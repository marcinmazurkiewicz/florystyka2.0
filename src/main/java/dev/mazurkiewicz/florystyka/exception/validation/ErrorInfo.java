package dev.mazurkiewicz.florystyka.exception.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorInfo {

    private final ErrorType errorType;
    private final String msg;

}
