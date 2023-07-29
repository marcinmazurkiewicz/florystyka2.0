package io.mazurkiewicz.quizer.question.infrastructure

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.NOT_FOUND)
open class ResourceNotFoundException(message: String) : Exception(message)

class QuestionNotFoundException(questionId: UUID): ResourceNotFoundException("Question $questionId not found")

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class QuestionNumberIsIncorrectException(expectedQuestionNumber: Int, searchedQuestionNumber: Int):
    Exception("Searched for $expectedQuestionNumber questions, only $searchedQuestionNumber were found")