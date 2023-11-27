package ru.vsu.cs.edryshov_ad.cg.triangulation;

import ru.vsu.cs.edryshov_ad.cg.math.Vector2f;

import java.util.*;

public class DelaunayTriangulation {
    private static final float GAP = 10;

    private static Triangle findSuperTriangle(List<? extends Vector2f> points) {
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

    public static Set<Triangle> bowyerWatsonAlgorithm(List<Vertex> points) {
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
}
