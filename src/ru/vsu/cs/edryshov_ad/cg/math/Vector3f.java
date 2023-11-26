package ru.vsu.cs.edryshov_ad.cg.math;

public class Vector3f {
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public boolean equals(Vector3f other) {
        return Math.abs(x - other.x) < MathConstants.EPSILON &&
                Math.abs(y - other.y) < MathConstants.EPSILON &&
                Math.abs(z - other.z) < MathConstants.EPSILON;
    }
}
