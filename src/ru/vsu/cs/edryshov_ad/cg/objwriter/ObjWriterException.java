package ru.vsu.cs.edryshov_ad.cg.objwriter;

public class ObjWriterException extends RuntimeException {
    public ObjWriterException(String errorMessage) {
        super("Error writing OBJ file: " + errorMessage);
    }
}
