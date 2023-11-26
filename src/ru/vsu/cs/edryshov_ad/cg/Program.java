package ru.vsu.cs.edryshov_ad.cg;

import ru.vsu.cs.edryshov_ad.cg.triangulation.DelaunayTriangulation;
import ru.vsu.cs.edryshov_ad.cg.triangulation.Triangle;
import ru.vsu.cs.edryshov_ad.cg.triangulation.Vertex;

import java.util.List;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        List<Vertex> vertices = List.of(
                new Vertex(0, 0, 0),
                new Vertex(1, 0, 1),
                new Vertex(1, 1, 2),
                new Vertex(0, 1, 3)
//                new Vertex(0, 0, 0),
//                new Vertex(2, 0, 1),
//                new Vertex(2.5f, 2.5f, 2),
//                new Vertex(1, 5, 3),
//                new Vertex(-0.5f, 2.5f, 4)
        );

        Set<Triangle> triangulated = DelaunayTriangulation.bowyerWatsonAlgorithm(vertices);

        for (Triangle triangle : triangulated) {
            System.out.println(triangle);
        }
    }
}
