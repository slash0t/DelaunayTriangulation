package ru.vsu.cs.edryshov_ad.cg.triangulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.edryshov_ad.cg.math.Vector3f;
import ru.vsu.cs.edryshov_ad.cg.model.Model;
import ru.vsu.cs.edryshov_ad.cg.model.Polygon;
import ru.vsu.cs.edryshov_ad.cg.model.TriangulatedModel;

import java.util.*;

public class DelaunayTriangulationTest {
    public Map<Polygon, List<Polygon>> getTriangulatedPolygons(Model old, TriangulatedModel now) {
        Map<Polygon, List<Polygon>> mapOfNewPolygons = new TreeMap<>();
        for (Polygon parent : old.getPolygons()) {
            if (parent.getSize() < 4) {
                mapOfNewPolygons.put(parent, List.of(parent));
                continue;
            }

            Set<Integer> vertexIndexes = new TreeSet<>();
            for (int i = 0; i < parent.getSize(); i++) {
                vertexIndexes.add(parent.getVertexIndex(i));
            }

            List<Polygon> newPolygons = new LinkedList<>();
            for (Polygon child : now.getPolygons()) {
                boolean allVerticesInPolygon = true;
                for (int i = 0; i < child.getSize(); i++) {
                    if (!vertexIndexes.contains(child.getVertexIndex(i))) {
                        allVerticesInPolygon = false;
                    }
                }

                if (allVerticesInPolygon) {
                    newPolygons.add(child);
                }
            }
            mapOfNewPolygons.put(parent, newPolygons);
        }
        return mapOfNewPolygons;
    }

    public boolean pointInsideTrianglePolygon(Vector3f point, Polygon polygon, Model model) {
        Vector3f a = model.getVertex(polygon.getVertexIndex(0));
        Vector3f b = model.getVertex(polygon.getVertexIndex(1));
        Vector3f c = model.getVertex(polygon.getVertexIndex(2));

        Vector3f ab = b.minus(a);
        Vector3f ac = c.minus(a);
        Vector3f ad = point.minus(a);

        Vector3f cad = ac.cross(ad);
        Vector3f dab = ad.cross(ab);

        if (cad.dot(dab) < 0) {
            return false;
        }

        Vector3f cd = point.minus(c);
        Vector3f ca = a.minus(c);
        Vector3f cb = b.minus(c);

        Vector3f acd = ca.cross(cd);
        Vector3f dcb = cd.cross(cb);

        return acd.dot(dcb) > 0;
    }

    public boolean trianglePolygonsIntersect(Collection<Polygon> polygons, Model model) {
        for (Polygon polygon1 : polygons) {
            for (Polygon polygon2 : polygons) {
                if (polygon1 == polygon2) {
                    continue;
                }

                for (int i = 0; i < 3; i++) {
                    Vector3f point = model.getVertex(polygon2.getVertexIndex(i));
                    if (pointInsideTrianglePolygon(point, polygon1, model)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean testModel(Model oldModel, TriangulatedModel newModel) {
        Map<Polygon, List<Polygon>> associatedPolygons = getTriangulatedPolygons(oldModel, newModel);

        boolean result = true;
        for (Map.Entry<Polygon, List<Polygon>> entry : associatedPolygons.entrySet()) {
            Polygon parent = entry.getKey();
            List<Polygon> triangles = entry.getValue();

            if (parent.getSize() - 2 != triangles.size()) {
                result = false;
            }

            if (trianglePolygonsIntersect(triangles, newModel)) {
                result = false;
            }
        }
        return result;
    }

    @Test
    public void testTriangulateRectangle() {
        ArrayList<Vector3f> vertices = new ArrayList<>(List.of(
                new Vector3f(0, 1, 0),
                new Vector3f(1, 1, 0),
                new Vector3f(1, 0, 0),
                new Vector3f(0, 0, 0)
        ));
        LinkedList<Polygon> polygons = new LinkedList<>(List.of(new Polygon(
                new ArrayList<>(List.of(0, 1, 2, 3)),
                new ArrayList<>(),
                new ArrayList<>()
        )));
        Model startModel = new Model(vertices, new ArrayList<>(), new ArrayList<>(), polygons);
        TriangulatedModel newModel = TriangulatedModel.triangulate(startModel);

        Assertions.assertTrue(testModel(startModel, newModel));
    }

    @Test
    public void testTriangulateOctagon() {
        ArrayList<Vector3f> vertices = new ArrayList<>(List.of(
                new Vector3f(0, 1, 0),
                new Vector3f(1, 0, 0),
                new Vector3f(2, 0, 0),
                new Vector3f(3, 1, 0),
                new Vector3f(3, 2, 0),
                new Vector3f(2, 3, 0),
                new Vector3f(1, 3, 0),
                new Vector3f(0, 2, 0)
        ));
        LinkedList<Polygon> polygons = new LinkedList<>(List.of(new Polygon(
                new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7)),
                new ArrayList<>(),
                new ArrayList<>()
        )));
        Model startModel = new Model(vertices, new ArrayList<>(), new ArrayList<>(), polygons);
        TriangulatedModel newModel = TriangulatedModel.triangulate(startModel);

        Assertions.assertTrue(testModel(startModel, newModel));
    }

    @Test
    public void testTriangulateTiltedRectangle() {
        ArrayList<Vector3f> vertices = new ArrayList<>(List.of(
                new Vector3f(0, 1, -2),
                new Vector3f(1, 1, -2),
                new Vector3f(1, 0, 1),
                new Vector3f(0, 0, 1)
        ));
        LinkedList<Polygon> polygons = new LinkedList<>(List.of(new Polygon(
                new ArrayList<>(List.of(0, 1, 2, 3)),
                new ArrayList<>(),
                new ArrayList<>()
        )));
        Model startModel = new Model(vertices, new ArrayList<>(), new ArrayList<>(), polygons);
        TriangulatedModel newModel = TriangulatedModel.triangulate(startModel);

        Assertions.assertTrue(testModel(startModel, newModel));
    }

    @Test
    public void testTriangulateTiltedOctagon() {
        ArrayList<Vector3f> vertices = new ArrayList<>(List.of(
                new Vector3f(0, 1, 2),
                new Vector3f(1, 0, 1),
                new Vector3f(2, 0, 1),
                new Vector3f(3, 1, 2),
                new Vector3f(3, 2, 3),
                new Vector3f(2, 3, 4),
                new Vector3f(1, 3, 5),
                new Vector3f(0, 2, 3)
        ));
        LinkedList<Polygon> polygons = new LinkedList<>(List.of(new Polygon(
                new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7)),
                new ArrayList<>(),
                new ArrayList<>()
        )));
        Model startModel = new Model(vertices, new ArrayList<>(), new ArrayList<>(), polygons);
        TriangulatedModel newModel = TriangulatedModel.triangulate(startModel);

        Assertions.assertTrue(testModel(startModel, newModel));
    }
}
