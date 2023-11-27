package ru.vsu.cs.edryshov_ad.cg.math;

public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float distanceTo(Vector2f vector) {
        float difX = x - vector.getX();
        float difY = y - vector.getY();
        return (float) Math.sqrt(difX * difX + difY * difY);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}
