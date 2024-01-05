package ru.vsu.cs.edryshov_ad.cg.model.exceptions;

public class CollectionSizeException extends RuntimeException{
    public CollectionSizeException(int count) {
        super("Wrong collection size was found: " + count);
    }
}
