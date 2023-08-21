package pl.quizwiz.question.infrastructure

import pl.quizwiz.exception.NotFoundApiException
import pl.quizwiz.exception.NotValidApiException
import pl.quizwiz.question.domain.model.AnswerType
import java.util.*

class QuestionWithoutCorrectAnswerException(questionId: UUID) :
    NotValidApiException("Question $questionId has no correct answer", "QW_1003")

class QuestionInvalidAnswersStateException(answerType: AnswerType, occurrences: Int) :
    NotValidApiException("Answer with type $answerType occurs $occurrences times", "QW_1004")

class QuestionNotFoundException(questionId: UUID) : NotFoundApiException("Question $questionId not found", "QW_1005")