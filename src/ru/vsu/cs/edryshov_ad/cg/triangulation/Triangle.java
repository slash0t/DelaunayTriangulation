package ru.vsu.cs.edryshov_ad.cg.triangulation;

import ru.vsu.cs.edryshov_ad.cg.math.MathConstants;
import ru.vsu.cs.edryshov_ad.cg.math.Vector2f;

import java.util.List;

public class Triangle implements Comparable<Triangle>{
    private final Vertex a;
    private final Vertex b;
    private final Vertex c;

    private final Vector2f circumCentre;

    private final float circumCircleRadius;

    public Triangle(Vertex a, Vertex b, Vertex c) {
        this.a = a;
        this.b = b;
        this.c = c;

        float denominator = 2 * (
                a.getX() * (b.getY() - c.getY()) +
                b.getX() * (c.getY() - a.getY()) +
                c.getX() * (a.getY() - b.getY())
        );
        float distA = a.getX() * a.getX() + a.getY() * a.getY();
        float distB = b.getX() * b.getX() + b.getY() * b.getY();
        float distC = c.getX() * c.getX() + c.getY() * c.getY();

        circumCentre = new Vector2f(
                (distA * (b.getY() - c.getY()) +
                        distB * (c.getY() - a.getY()) +
                        distC * (a.getY() - b.getY())
                ) / denominator,
                (distA * (c.getX() - b.getX()) +
                        distB * (a.getX() - c.getX()) +
                        distC * (b.getX() - a.getX())
                ) / denominator
        );

        circumCircleRadius = circumCentre.distanceTo(a);
    }

    public Vertex getA() {
        return a;
    }

    public Vertex getB() {
        return b;
    }

    public Vertex getC() {
        return c;
    }

    public List<Vertex> getPoints() {
        return List.of(a, b, c);
    }

    public boolean containsNotIndexedVertices() {
        return a.getIndex() == -1 || b.getIndex() == -1 || c.getIndex() == -1;
    }

    public boolean pointInsideCircumcircle(Vector2f point) {
        return circumCentre.distanceTo(point) - circumCircleRadius <= MathConstants.EPSILON;
    }

    public boolean containsEdge(Edge edge) {
        return (edge.getA().equals(a) || edge.getA().equals(b) || edge.getA().equals(c)) &&
                (edge.getB().equals(a) || edge.getB().equals(b) || edge.getB().equals(c));
    }

    public List<Edge> getEdges() {
        return List.of(new Edge(a, b), new Edge(b, c), new Edge(a, c));
    }

    @Override
    public int compareTo(Triangle t) {
        int temp = a.compareTo(t.getA());

        if (temp == 0) {
            int temp2 = b.compareTo(t.getB());

            if (temp2 == 0) {
                return c.compareTo(t.getC());
            }

            return temp2;
        }

        return temp;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", a, b, c);
    }
}
