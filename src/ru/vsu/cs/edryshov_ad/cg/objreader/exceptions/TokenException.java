package ru.vsu.cs.edryshov_ad.cg.objreader.exceptions;

public class TokenException extends ObjReaderException {
    public TokenException(int lineIndex) {
        super("Invalid line beginning.", lineIndex);
    }
}
