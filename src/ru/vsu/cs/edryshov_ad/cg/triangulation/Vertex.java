package ru.vsu.cs.edryshov_ad.cg.triangulation;

import ru.vsu.cs.edryshov_ad.cg.math.MathConstants;
import ru.vsu.cs.edryshov_ad.cg.math.Vector2f;

public class Vertex extends Vector2f implements Comparable<Vertex> {
    private final int index;

    public Vertex(float x, float y, int index) {
        super(x, y);
        this.index = index;
    }

    public Vertex(float x, float y) {
        super(x, y);
        this.index = -1;
    }

    public int getIndex() {
        return index;
    }

    public boolean equals(Vertex v) {
        return Math.abs(getX() - v.getX()) < MathConstants.EPSILON &&
                Math.abs(getY() - v.getY()) < MathConstants.EPSILON;
    }

    @Override
    public int compareTo(Vertex v) {
        if (this.equals(v)) {
            return 0;
        }

        float difX = getX() - v.getX();

        if (Math.abs(difX) < MathConstants.EPSILON) {
            float difY = getY() - v.getY();

            return difY > 0 ? 1 : -1;
        }

        return difX > 0 ? 1 : -1;
    }
}
