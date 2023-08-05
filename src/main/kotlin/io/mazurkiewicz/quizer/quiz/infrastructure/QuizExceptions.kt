package io.mazurkiewicz.quizer.quiz.infrastructure

import io.mazurkiewicz.quizer.question.infrastructure.ResourceNotFoundException
import java.util.*

class QuizTemplateNotFoundException(quizTemplateId: UUID): ResourceNotFoundException("Quiz template $quizTemplateId not found")
