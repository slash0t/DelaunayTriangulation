package ru.vsu.cs.edryshov_ad.cg.model;

import java.util.ArrayList;

public class Polygon {

    private ArrayList<Integer> vertexIndices;
    private ArrayList<Integer> textureVertexIndices;
    private ArrayList<Integer> normalIndices;

    public Polygon() {
        vertexIndices = new ArrayList<>();
        textureVertexIndices = new ArrayList<>();
        normalIndices = new ArrayList<>();
    }

    public void setVertexIndices(ArrayList<Integer> vertexIndices) {
        assert vertexIndices.size() >= 3;
        this.vertexIndices = vertexIndices;
    }

    public void setTextureVertexIndices(ArrayList<Integer> textureVertexIndices) {
        assert textureVertexIndices.size() >= 3;
        this.textureVertexIndices = textureVertexIndices;
    }

    public void setNormalIndices(ArrayList<Integer> normalIndices) {
        assert normalIndices.size() >= 3;
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
}