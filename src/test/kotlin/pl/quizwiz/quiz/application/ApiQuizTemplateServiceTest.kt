package pl.quizwiz.quiz.application

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import pl.quizwiz.quiz.domain.model.*
import pl.quizwiz.quiz.domain.port.QuizTemplateService
import java.util.*

class ApiQuizTemplateServiceTest {

    private val service = mockk<QuizTemplateService>()
    private val apiService = ApiQuizTemplateService(service)

    @Test
    fun `should map parameters and call QuizTemplateService`() {
        //given
        val name = "template name"
        val accessType = TemplateAccessType.PUBLIC
        val drawParams = DefaultDrawParams(DrawType.NONE)
        val threshold = 50
        val expectedTemplateId = UUID.randomUUID()

        every {
            service.createTemplate(
                TemplateName(name),
                any(),
                accessType,
                TemplateDrawSettings(DrawType.NONE),
                TemplatePassThreshold(threshold)
            )
        } returns TemplateId(expectedTemplateId)

        //when
        val templateId = apiService.createNewQuizTemplate(name, accessType, drawParams, threshold, UUID.randomUUID())

        //then
        verify(exactly = 1) {
            service.createTemplate(
                TemplateName(name),
                any(),
                accessType,
                TemplateDrawSettings(DrawType.NONE),
                TemplatePassThreshold(threshold)
            )
        }
        assertThat(templateId).isEqualTo(expectedTemplateId)
    }
}
