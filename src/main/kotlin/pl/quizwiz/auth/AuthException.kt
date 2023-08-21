package pl.quizwiz.auth

import pl.quizwiz.exception.NotValidApiException
import pl.quizwiz.exception.UnauthorizedApiException

class EmailAlreadyExistException(email: String) :
    NotValidApiException("Email $email is already registered", "M2AUTH_1001")

class CredentialsNotValidException :
    UnauthorizedApiException("Incorrect email or password", "M2AUTH_1002")
