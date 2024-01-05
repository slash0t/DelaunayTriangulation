package ru.vsu.cs.edryshov_ad.cg.objreader.exceptions;

public class ArgumentsSizeException extends ObjReaderException {
    public ArgumentsSizeException(ArgumentsErrorType errorType, int lineIndex) {
        super("Too " + errorType.getTextValue() + " arguments.", lineIndex);
    }
}
