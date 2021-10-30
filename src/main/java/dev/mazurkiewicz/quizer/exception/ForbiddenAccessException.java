package dev.mazurkiewicz.quizer.exception;

public class ForbiddenAccessException extends RuntimeException {

    public ForbiddenAccessException(String msg) {
        super(msg);
    }

}
