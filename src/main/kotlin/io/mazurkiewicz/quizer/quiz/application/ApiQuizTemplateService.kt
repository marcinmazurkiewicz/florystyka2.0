package io.mazurkiewicz.quizer.quiz.application

import io.mazurkiewicz.quizer.quiz.domain.model.*
import io.mazurkiewicz.quizer.quiz.domain.port.QuizTemplateService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApiQuizTemplateService(private val templateService: QuizTemplateService) {
    fun createNewQuizTemplate(name: String, accessType: TemplateAccessType, drawParams: DefaultDrawParams, defaultPassPercentageThreshold: Int): UUID {
        val quizTemplateId = templateService.createTemplate(
            TemplateName(name),
            TemplateAuthor(UUID.randomUUID()),
            accessType,
            TemplateDrawSettings(drawParams.type, drawParams.questionsNumber),
            TemplatePassThreshold(defaultPassPercentageThreshold)
        )
        return quizTemplateId.id
    }

}