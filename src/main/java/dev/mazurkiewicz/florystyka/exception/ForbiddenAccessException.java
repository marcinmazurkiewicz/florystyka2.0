package dev.mazurkiewicz.florystyka.exception;

public class ForbiddenAccessException extends RuntimeException {

    public ForbiddenAccessException(String msg) {
        super(msg);
    }

}
