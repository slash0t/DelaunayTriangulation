package ru.vsu.cs.edryshov_ad.cg.model;

import ru.vsu.cs.edryshov_ad.cg.math.Vector2f;
import ru.vsu.cs.edryshov_ad.cg.math.Vector3f;

import java.util.*;

public class Model {

    private ArrayList<Vector3f> vertices;
    private ArrayList<Vector2f> textureVertices;
    private ArrayList<Vector3f> normals;

    private LinkedList<Polygon> polygons;

    public Model(
            ArrayList<Vector3f> vertices, ArrayList<Vector2f> textureVertices,
            ArrayList<Vector3f> normals, LinkedList<Polygon> polygons
    ) {
        this.vertices = vertices;
        this.textureVertices = textureVertices;
        this.normals = normals;
        this.polygons = polygons;
    }

    public Model() {
        this.vertices = new ArrayList<>();
        this.textureVertices = new ArrayList<>();
        this.normals = new ArrayList<>();
        this.polygons = new LinkedList<>();
    }

    public Vector3f getVertex(int index) {
        return vertices.get(index);
    }

    public Vector2f getTextureVertex(int index) {
        return textureVertices.get(index);
    }

    public Vector3f getNormal(int index) {
        return normals.get(index);
    }

    public Iterator<Polygon> getPolygonIterator() {
        return polygons.iterator();
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getTextureVertexCount() {
        return textureVertices.size();
    }

    public int getNormalCount() {
        return normals.size();
    }
}
