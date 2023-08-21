package pl.quizwiz.quiz.application

import org.springframework.stereotype.Service
import pl.quizwiz.quiz.domain.model.*
import pl.quizwiz.quiz.domain.port.QuizTemplateService
import java.util.*

@Service
class ApiQuizTemplateService(private val templateService: QuizTemplateService) {
    fun createNewQuizTemplate(
        name: String,
        accessType: TemplateAccessType,
        drawParams: DefaultDrawParams,
        defaultPassPercentageThreshold: Int,
        userId: UUID
    ): UUID {
        val quizTemplateId = templateService.createTemplate(
            TemplateName(name),
            TemplateAuthor(userId),
            accessType,
            TemplateDrawSettings(drawParams.type, drawParams.questionsNumber),
            TemplatePassThreshold(defaultPassPercentageThreshold)
        )
        return quizTemplateId.id
    }

}