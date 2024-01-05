package ru.vsu.cs.edryshov_ad.cg;

import ru.vsu.cs.edryshov_ad.cg.model.Model;
import ru.vsu.cs.edryshov_ad.cg.model.TriangulatedModel;
import ru.vsu.cs.edryshov_ad.cg.objreader.ObjReader;
import ru.vsu.cs.edryshov_ad.cg.objwriter.ObjWriter;

import java.io.File;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String output = scanner.next();

        try {
            Model startModel = ObjReader.read(new File(input));
            Model newModel = TriangulatedModel.triangulate(startModel);
            ObjWriter.write(output, newModel);
        } catch (RuntimeException ex) {
            System.out.println("Возникла ошибка: " + ex);
        }
    }
}
