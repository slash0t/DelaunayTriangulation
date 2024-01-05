package ru.vsu.cs.edryshov_ad.cg.model;

import java.util.*;

public class Polygon implements Comparable<Polygon> {

    private final ArrayList<Integer> vertexIndices;
    private final ArrayList<Integer> textureVertexIndices;
    private final ArrayList<Integer> normalIndices;

    public Polygon(Polygon polygon) {
        int size = polygon.getSize();

        vertexIndices = new ArrayList<>(size);
        textureVertexIndices = new ArrayList<>(size);
        normalIndices = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            vertexIndices.add(i, polygon.getVertexIndex(i));
            if (polygon.areTexturesSet()) {
                textureVertexIndices.add(i, polygon.getTextureVertexIndex(i));
            }
            if (polygon.areNormalsSet()) {
                normalIndices.add(i, polygon.getNormalIndex(i));
            }
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
        return vertexIndices.size() == textureVertexIndices.size();
    }

    public boolean areNormalsSet() {
        return vertexIndices.size() == normalIndices.size();
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        sj.add(vertexIndices.toString());
        return sj.toString();
    }

    public ArrayList<Integer> getVertexIndices() {
        return new ArrayList<>(vertexIndices);
    }

    public ArrayList<Integer> getTextureVertexIndices() {
        return new ArrayList<>(textureVertexIndices);
    }

    public ArrayList<Integer> getNormalIndices() {
        return new ArrayList<>(normalIndices);
    }

    @Override
    public int compareTo(Polygon o) {
        if (getSize() != o.getSize()) {
            return Integer.compare(getSize(), o.getSize());
        }

        TreeSet<Integer> set = new TreeSet<>(vertexIndices);
        TreeSet<Integer> oSet = new TreeSet<>(o.vertexIndices);

        Iterator<Integer> it = set.iterator();
        Iterator<Integer> oIt = oSet.iterator();
        for (int i = 0; i < getSize(); i++) {
            int n1 = it.next();
            int n2 = oIt.next();
            if (n1 != n2) {
                return Integer.compare(n1, n2);
            }
        }
        return 0;
    }
}
