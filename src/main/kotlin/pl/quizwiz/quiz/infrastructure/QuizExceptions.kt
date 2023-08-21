package pl.quizwiz.quiz.infrastructure

import pl.quizwiz.exception.ForbiddenApiException
import pl.quizwiz.exception.NotFoundApiException
import java.util.*

class QuizTemplateNotFoundException(quizTemplateId: UUID) :
    NotFoundApiException("Quiz template $quizTemplateId not found", "QW_1001")

class IllegalTemplateAccessException(userId: UUID, templateId: UUID) :
    ForbiddenApiException("User $userId not has access to modify template $templateId", "QW_1002")