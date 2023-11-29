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

    public void normalize() {
        float dist = (float) Math.sqrt(x * x + y * y + z * z);

        x /= dist;
        y /= dist;
        z /= dist;
    }

    public Vector3f cross(Vector3f vector) {
        return new Vector3f(
          y * vector.getZ() - z * vector.getY(),
          z * vector.getX() - x * vector.getZ(),
          x * vector.getY() - y * vector.getX()
        );
    }

    public Vector3f minus(Vector3f vector) {
        return new Vector3f(
                getX() - vector.getX(),
                getY() - vector.getY(),
                getZ() - vector.getZ()
        );
    }
}
