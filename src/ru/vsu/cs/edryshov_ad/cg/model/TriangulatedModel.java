package ru.vsu.cs.edryshov_ad.cg.model;

import ru.vsu.cs.edryshov_ad.cg.math.Vector2f;
import ru.vsu.cs.edryshov_ad.cg.math.Vector3f;
import ru.vsu.cs.edryshov_ad.cg.triangulation.DelaunayTriangulation;

import java.util.LinkedList;
import java.util.function.Function;

public class TriangulatedModel extends Model {
    private static Function<Model, LinkedList<TrianglePolygon>> DEFAULT_TRIANGULATION = DelaunayTriangulation::getTriangulation;
    private final LinkedList<TrianglePolygon> triangulatedPolygons;

    private TriangulatedModel(Model model, LinkedList<TrianglePolygon> triangulatedPolygons) {
        super(
                model.getVertices(), model.getTextureVertices(),
                model.getNormals(), model.getPolygons()
        );

        this.triangulatedPolygons = triangulatedPolygons;
    }

    public static TriangulatedModel triangulate(Model model, Function<Model, LinkedList<TrianglePolygon>> function) {
        LinkedList<TrianglePolygon> polygons = function.apply(model);
        return new TriangulatedModel(model, polygons);
    }

    public static TriangulatedModel triangulate(Model model) {
        return triangulate(model, DEFAULT_TRIANGULATION);
    }

    public static void changeDefaultTriangulation(Function<Model, LinkedList<TrianglePolygon>> function) {
        if (function == null) {
            return;
        }

        DEFAULT_TRIANGULATION = function;
    }

    @Override
    public LinkedList<Polygon> getPolygons() {
        return new LinkedList<>(triangulatedPolygons);
    }

    @Override
    public void addVertex(Vector3f vertex) {
        return;
    }

    @Override
    public void addTextureVertex(Vector2f textureVertex) {
        return;
    }

    @Override
    public void addNormal(Vector3f normal) {
        return;
    }

    @Override
    public void addGroup(Group group) {
        return;
    }

    @Override
    public void addPolygon(Polygon polygon) {
        return;
    }

    public LinkedList<Polygon> getOriginPolygons() {
        return new LinkedList<>(super.getPolygons());
    }
}
