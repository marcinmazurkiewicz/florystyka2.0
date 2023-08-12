package io.mazurkiewicz.quizer.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ApiError(
    val status: HttpStatus,
    val path: String,
    val message: String,
    val errorCode: String
) {
    fun toEntity() = ResponseEntity(this, status)
}

