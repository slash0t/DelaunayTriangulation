package ru.vsu.cs.edryshov_ad.cg.model;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Polygon {

    private final ArrayList<Integer> vertexIndices;
    private final ArrayList<Integer> textureVertexIndices;
    private final ArrayList<Integer> normalIndices;

    public Polygon() {
        vertexIndices = new ArrayList<>();
        textureVertexIndices = new ArrayList<>();
        normalIndices = new ArrayList<>();
    }

    public Polygon(Polygon polygon) {
        int size = polygon.getSize();

        vertexIndices = new ArrayList<>(size);
        textureVertexIndices = new ArrayList<>(size);
        normalIndices = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            vertexIndices.add(i, polygon.getVertexIndex(i));
            textureVertexIndices.add(i, polygon.getTextureVertexIndex(i));
            normalIndices.add(i, polygon.getNormalIndex(i));
        }
    }

    public Polygon(ArrayList<Integer> vertexIndices, ArrayList<Integer> textureVertexIndices, ArrayList<Integer> normalIndices) {
        this.vertexIndices = vertexIndices;
        this.textureVertexIndices = textureVertexIndices;
        this.normalIndices = normalIndices;
    }

    public int getVertexIndex(int index) {
        return vertexIndices.get(index);
    }

    public int getTextureVertexIndex(int index) {
        return textureVertexIndices.get(index);
    }

    public int getNormalIndex(int index) {
        return normalIndices.get(index);
    }

    public int getSize() {
        return vertexIndices.size();
    }

    public boolean areTexturesSet() {
        return vertexIndices.size() <= textureVertexIndices.size();
    }

    public boolean areNormalsSet() {
        return vertexIndices.size() <= normalIndices.size();
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        sj.add(vertexIndices.toString());
        return sj.toString();
    }
}
