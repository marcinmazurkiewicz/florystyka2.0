package dev.mazurkiewicz.quizer.exception;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String msg) {
        super(msg);
    }
}
