package io.mazurkiewicz.quizer.auth

import io.mazurkiewicz.quizer.exception.NotValidApiException
import io.mazurkiewicz.quizer.exception.UnauthorizedApiException

class EmailAlreadyExistException(email: String) :
    NotValidApiException("Email $email is already registered", "M2AUTH_1001")

class CredentialsNotValidException :
    UnauthorizedApiException("Incorrect email or password", "M2AUTH_1002")
