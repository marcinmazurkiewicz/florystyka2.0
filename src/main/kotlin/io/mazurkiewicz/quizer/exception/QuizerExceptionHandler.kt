package io.mazurkiewicz.quizer.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class QuizerExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(
        exception: ApiException,
        request: WebRequest
    ): ResponseEntity<ApiError> {
        val requestPath = (request as ServletWebRequest).request.requestURI
        val apiError = ApiError(exception.httpStatus, requestPath, exception.message, exception.errorCode)
        return apiError.toEntity()
    }
}