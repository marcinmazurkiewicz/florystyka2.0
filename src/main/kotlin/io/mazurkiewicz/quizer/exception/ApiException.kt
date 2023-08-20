package io.mazurkiewicz.quizer.exception

import org.springframework.http.HttpStatus

sealed class ApiException(
    val httpStatus: HttpStatus,
    override val message: String,
    open val errorCode: String
) : RuntimeException(message)

open class NotFoundApiException(override val message: String, override val errorCode: String) :
    ApiException(HttpStatus.NOT_FOUND, message, errorCode)

open class NotValidApiException(override val message: String, override val errorCode: String) :
    ApiException(HttpStatus.BAD_REQUEST, message, errorCode)

open class UnauthorizedApiException(override val message: String, override val errorCode: String) :
    ApiException(HttpStatus.UNAUTHORIZED, message, errorCode)

open class ForbiddenApiException(override val message: String, override val errorCode: String) :
    ApiException(HttpStatus.FORBIDDEN, message, errorCode)