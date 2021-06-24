package dev.mazurkiewicz.florystyka.exception.validation;

import java.util.Arrays;
import java.util.List;

public enum ErrorType {
    ABOVE_MAX(List.of("DecimalMax", "Max")),
    AT_LEAST_ONE(List.of("AtLeastOne")),
    CAPTCHA_ERROR(List.of()),
    CREDENTIALS_ERROR(List.of("BadCredentialsException")),
    DOCUMENT_CONTENT_NOT_VALID(List.of("IllegalImportDocumentContent")),
    EMPTY(List.of("NotEmpty", "NotNull", "NotBlank", "NotNullFile")),
    FILE_PROCESSING_ERROR(List.of("IOException")),
    FORBIDDEN(List.of("ForbiddenAccessException", "NewUserRoles")),
    NOT_MAIL(List.of("Email")),
    NOT_MATCH(List.of("FieldMatch")),
    NOT_UNIQUE(List.of("UniqueUsername")),
    PARSE_ERROR(List.of("HttpMessageNotReadableException")),
    SIZE(List.of("Size", "Length")),
    TOKEN_EXPIRED(List.of("TokenExpiredException", "ExpiredJwtException")),
    TYPE_MISMATCH(List.of("FileFormat", "FileTypeException")),
    UNAUTHORIZED(List.of("UnauthorizedAccessException")),
    UNDER_MIN(List.of("DecimalMin", "Min")),
    UNKNOWN(List.of()),
    VALIDATION_ERROR(List.of("BindException", "MethodArgumentNotValidException"));

    private final List<String> codeNames;

    ErrorType(List<String> codeNames) {
        this.codeNames = codeNames;
    }

    public static ErrorType valueOfCode(String codeName) {

        return Arrays.stream(ErrorType.values())
                .filter(errorType -> errorType.codeNames.contains(codeName))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
