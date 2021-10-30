package dev.mazurkiewicz.quizer.exception;

public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String msg) {
        super(msg);
    }

}
