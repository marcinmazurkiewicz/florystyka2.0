package io.mazurkiewicz.quizer.question.infrastructure

import io.mazurkiewicz.quizer.exception.NotFoundApiException
import io.mazurkiewicz.quizer.exception.NotValidApiException
import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import java.util.*

class QuestionWithoutCorrectAnswerException(questionId: UUID) : NotValidApiException("Question $questionId has no correct answer", "QW_1003")

class QuestionInvalidAnswersStateException(answerType: AnswerType, occurrences: Int) : NotValidApiException("Answer with type $answerType occurs $occurrences times", "QW_1004")

class QuestionNotFoundException(questionId: UUID) : NotFoundApiException("Question $questionId not found", "QW_1005")