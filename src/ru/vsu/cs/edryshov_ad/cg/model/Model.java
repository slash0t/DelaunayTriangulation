package ru.vsu.cs.edryshov_ad.cg.model;

import ru.vsu.cs.edryshov_ad.cg.math.Vector2f;
import ru.vsu.cs.edryshov_ad.cg.math.Vector3f;

import java.util.*;

public class Model {

    private final ArrayList<Vector3f> vertices;
    private final ArrayList<Vector2f> textureVertices;
    private final ArrayList<Vector3f> normals;

    private final LinkedList<Polygon> polygons;

    private final List<Group> groups = new ArrayList<>();

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

    public int getVertexCount() {
        return vertices.size();
    }

    public int getTextureVertexCount() {
        return textureVertices.size();
    }

    public int getNormalCount() {
        return normals.size();
    }

    public void addVertex(Vector3f vertex) {
        vertices.add(vertex);
    }

    public void addTextureVertex(Vector2f textureVertex) {
        textureVertices.add(textureVertex);
    }

    public void addNormal(Vector3f normal) {
        normals.add(normal);
    }

    public void addPolygon(Polygon polygon) {
        polygons.add(polygon);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Polygon getFirstPolygon() {
        return polygons.getFirst();
    }

    public ArrayList<Vector3f> getVertices() {
        return new ArrayList<>(vertices);
    }

    public ArrayList<Vector2f> getTextureVertices() {
        return new ArrayList<>(textureVertices);
    }

    public ArrayList<Vector3f> getNormals() {
        return new ArrayList<>(normals);
    }

    public LinkedList<Polygon> getPolygons() {
        return new LinkedList<>(polygons);
    }

    public LinkedList<Group> getGroups() {
        return new LinkedList<>(groups);
    }
}
