package dev.mazurkiewicz.quizer.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.quizer.exception.validation.ErrorInfo;
import dev.mazurkiewicz.quizer.exception.validation.ErrorType;
import lombok.Value;

import java.util.Map;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    int status;
    String message;
    String path;
    ErrorType error;
    Map<String, ErrorInfo> errors;


    public ErrorResponse(int status, String message, String path, ErrorType error) {
        this(status, message, path, error, null);
    }

    public ErrorResponse(int status, String message, String path, ErrorType error, Map<String, ErrorInfo> errors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.error = error;
        this.errors = errors;
    }

}



