package io.mazurkiewicz.quizer.quiz.domain.port

import io.mazurkiewicz.quizer.quiz.domain.model.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class QuizTemplateServiceTest {
    private val quizTemplateService: QuizTemplateService = QuizTemplateService(InMemoryTemplateRepository())

    @Test
    fun `should create new quiz and return id`() {
        val templateName = TemplateName("test template")
        val templateAuthor = TemplateAuthor(UUID.randomUUID())
        val accessType = TemplateAccessType.PUBLIC
        val drawSettings = TemplateDrawSettings(DrawType.NONE)
        val defaultPercentPassThreshold = TemplatePassThreshold(51)

        val templateId = quizTemplateService.createTemplate(templateName, templateAuthor, accessType, drawSettings, defaultPercentPassThreshold)

        assertThat(templateId).isNotNull
    }

    @Test
    fun `should find and return created template`() {
        val templateName = TemplateName("test template to found")
        val templateAuthor = TemplateAuthor(UUID.randomUUID())
        val accessType = TemplateAccessType.PUBLIC
        val drawSettings = TemplateDrawSettings(DrawType.NONE)
        val defaultPercentPassThreshold = TemplatePassThreshold(51)
        val templateId = quizTemplateService.createTemplate(templateName, templateAuthor, accessType, drawSettings, defaultPercentPassThreshold)

        val searchedTemplate = quizTemplateService.findTemplate(templateId)

        assertThat(searchedTemplate.name.value).isEqualTo(templateName.value)
        assertThat(searchedTemplate.author.authorId).isEqualTo(templateAuthor.authorId)
        assertThat(searchedTemplate.accessType).isEqualTo(accessType)
        assertThat(searchedTemplate.defaultDrawSettings.type).isEqualTo(drawSettings.type)
        assertThat(searchedTemplate.defaultDrawSettings.questionNumber).isEqualTo(drawSettings.questionNumber)
        assertThat(searchedTemplate.templatePassThreshold.percentage).isEqualTo(defaultPercentPassThreshold.percentage)
    }

}