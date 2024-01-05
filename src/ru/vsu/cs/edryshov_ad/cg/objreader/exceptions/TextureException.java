package ru.vsu.cs.edryshov_ad.cg.objreader.exceptions;

public class TextureException extends ObjReaderException {
    public TextureException(int lineIndex) {
        super("Texture presence mismatch.", lineIndex);
    }
}
