package ru.vsu.cs.edryshov_ad.cg.triangulation;

import ru.vsu.cs.edryshov_ad.cg.math.Vector2f;
import ru.vsu.cs.edryshov_ad.cg.math.Vector3f;
import ru.vsu.cs.edryshov_ad.cg.model.Model;
import ru.vsu.cs.edryshov_ad.cg.model.Polygon;

import java.util.*;

public class DelaunayTriangulation {
    private static final float GAP = 10;

    private static Triangle findSuperTriangle(Collection<? extends Vector2f> points) {
        Iterator<? extends Vector2f> iterator = points.iterator();

        Vector2f point = iterator.next();
        float maxX = point.getX(), minX = point.getX();
        float maxY = point.getY(), minY = point.getY();

        while (iterator.hasNext()) {
            point = iterator.next();

            maxX = Math.max(maxX, point.getX());
            minX = Math.min(minX, point.getX());
            maxY = Math.max(maxY, point.getY());
            minY = Math.min(minY, point.getY());
        }

        maxX += GAP;
        minX -= GAP;
        maxY += GAP;
        minY -= GAP;

        float midX = (minX + maxX) / 2;
        float difX = maxX - minX;
        float difY = maxY - minY;

        return new Triangle(
                new Vertex(midX - difX, minY),
                new Vertex(midX + difX, minY),
                new Vertex(midX, minY + 2 * difY)
        );
    }

    private static Set<Triangle> bowyerWatsonAlgorithm(Collection<Vertex> points) {
        Set<Triangle> triangulation = new TreeSet<>();
        triangulation.add(findSuperTriangle(points));

        for (Vertex point : points) {
            TreeSet<Edge> polygonalHole = new TreeSet<>();
            TreeSet<Edge> badEdges = new TreeSet<>();

            Iterator<Triangle> triangleIterator = triangulation.iterator();
            while (triangleIterator.hasNext()) {
                Triangle triangle = triangleIterator.next();

                if (triangle.pointInsideCircumcircle(point)) {
                    triangleIterator.remove();

                    for (Edge edge : triangle.getEdges()) {
                        if (badEdges.add(edge)) {
                            polygonalHole.add(edge);
                        } else {
                            polygonalHole.remove(edge);
                        }
                    }
                }
            }

            for (Edge edge : polygonalHole) {
                Triangle newTriangle = new Triangle(point, edge.getA(), edge.getB());
                triangulation.add(newTriangle);
            }
        }

        triangulation.removeIf(Triangle::containsNotIndexedVertices);

        return triangulation;
    }

    private static List<Vector2f> getPointProjectedOnPlane(Collection<Vector3f> points) {
        Iterator<Vector3f> iterator = points.iterator();
        Vector3f a = iterator.next();
        Vector3f b = iterator.next();
        Vector3f c = iterator.next();

        Vector3f ab = b.minus(a);
        Vector3f bc = c.minus(b);

        Vector3f h = ab.cross(bc);
        Vector3f p = ab.cross(h);

        ab.normalize();
        p.normalize();

        LinkedList<Vector2f> pointsOnPlane = new LinkedList<>();
        for (Vector3f point : points) {
            Vector3f shift = point.minus(a);

            pointsOnPlane.add(new Vector2f(
                    shift.getX() * p.getX() + shift.getY() * p.getY() + shift.getZ() * p.getZ(),
                    shift.getX() * ab.getX() + shift.getY() * ab.getY() + shift.getZ() * ab.getZ()
            ));
        }

        return pointsOnPlane;
    }

    public static Model getTriangulatedModel(Model model) {
        ArrayList<Vector3f> vertices = new ArrayList<>(model.getVertexCount());
        for (int i = 0; i < model.getVertexCount(); i++) {
            vertices.add(i, model.getVertex(i));
        }

        ArrayList<Vector2f> textureVertices = new ArrayList<>(model.getTextureVertexCount());
        for (int i = 0; i < model.getTextureVertexCount(); i++) {
            textureVertices.add(i, model.getTextureVertex(i));
        }

        ArrayList<Vector3f> normals = new ArrayList<>(model.getNormalCount());
        for (int i = 0; i < model.getNormalCount(); i++) {
            normals.add(i, model.getNormal(i));
        }

        LinkedList<Polygon> polygons = new LinkedList<>();
        for (Iterator<Polygon> iterator = model.getPolygonIterator(); iterator.hasNext(); ) {
            Polygon polygon = iterator.next();

            if (polygon.getSize() < 4) {
                Polygon copy = new Polygon(polygon);
                polygons.add(copy);
            }

            LinkedList<Vector3f> pointsInSpace = new LinkedList<>();
            for (int i = 0; i < polygon.getSize(); i++) {
                int vertexIndex = polygon.getVertexIndex(i);
                pointsInSpace.add(vertices.get(vertexIndex));
            }

            List<Vector2f> pointsOnPlane = getPointProjectedOnPlane(pointsInSpace);
            LinkedList<Vertex> polygonVertices = new LinkedList<>();
            int i = 0;
            for (Vector2f vector : pointsOnPlane) {
                polygonVertices.add(new Vertex(
                        vector.getX(), vector.getY(), i
                ));
                i++;
            }

            Set<Triangle> triangles = bowyerWatsonAlgorithm(polygonVertices);
            for (Triangle triangle : triangles) {
                ArrayList<Integer> newVertices = new ArrayList<>(3);
                ArrayList<Integer> newTextureVertices = new ArrayList<>(3);
                ArrayList<Integer> newNormals = new ArrayList<>(3);

                for (Vertex vertex : triangle.getPoints()) {
                    int index = vertex.getIndex();

                    int vertexIndex = polygon.getVertexIndex(index);
                    newVertices.add(vertexIndex);

                    if (polygon.areTexturesSet()) {
                        int vertexTextureIndex = polygon.getVertexIndex(index);
                        newVertices.add(vertexTextureIndex);
                    }

                    if (polygon.areNormalsSet()) {
                        int normalIndex = polygon.getVertexIndex(index);
                        newVertices.add(normalIndex);
                    }
                }

                Polygon newPolygon = new Polygon(newVertices, newTextureVertices, newNormals);
                polygons.add(newPolygon);
            }
        }

        return new Model(vertices, textureVertices, normals, polygons);
    }
}
