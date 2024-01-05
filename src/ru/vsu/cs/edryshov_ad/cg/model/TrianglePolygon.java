package ru.vsu.cs.edryshov_ad.cg.model;

import ru.vsu.cs.edryshov_ad.cg.model.exceptions.CollectionSizeException;

import java.util.ArrayList;

public class TrianglePolygon extends Polygon {
    public TrianglePolygon(Polygon polygon) {
        super(polygon);
        if (polygon.getSize() != 3) {
            throw new CollectionSizeException(polygon.getSize());
        }
    }

    public TrianglePolygon(ArrayList<Integer> vertexIndices, ArrayList<Integer> textureVertexIndices, ArrayList<Integer> normalIndices) {
        super(vertexIndices, textureVertexIndices, normalIndices);
        int size = vertexIndices.size();
        if (size != 3) {
            throw new CollectionSizeException(size);
        }
        size = textureVertexIndices.size();
        if (size != 3 && size != 0) {
            throw new CollectionSizeException(size);
        }
        size = normalIndices.size();
        if (size != 3 && size != 0) {
            throw new CollectionSizeException(size);
        }
    }

    @Override
    public int getSize() {
        return 3;
    }
}
