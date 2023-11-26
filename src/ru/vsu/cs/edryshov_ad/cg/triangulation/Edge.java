package ru.vsu.cs.edryshov_ad.cg.triangulation;

public class Edge implements Comparable<Edge> {
    private final Vertex a;
    private final Vertex b;

    public Edge(Vertex a, Vertex b) {
        this.a = a;
        this.b = b;
    }

    public Vertex getA() {
        return a;
    }

    public Vertex getB() {
        return b;
    }


    @Override
    public int compareTo(Edge e) {
        int temp = a.compareTo(e.getA());

        if (temp == 0) {
            return b.compareTo(e.getB());
        }

        return temp;
    }
}
