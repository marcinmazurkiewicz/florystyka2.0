package io.mazurkiewicz.quizer.quiz.application

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mazurkiewicz.quizer.quiz.domain.model.DrawType
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateAccessType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import java.util.*

class QuizTemplateControllerTest {
    private val apiQuizTemplateService = mockk<ApiQuizTemplateService>()
    private val quizTemplateController = QuizTemplateController(apiQuizTemplateService)
    private val objectMapper = jacksonObjectMapper()
    private val mockMvc = standaloneSetup(quizTemplateController).build()

    @Test
    fun `should return template id when create template request is valid`() {
        //given
        val request = CreateQuizTemplateRequest(
            "test request",
            TemplateAccessType.PUBLIC,
            DefaultDrawParams(DrawType.SHUFFLE, 5),
            51
        )
        val templateId = UUID.randomUUID()

        every { apiQuizTemplateService.createNewQuizTemplate(any(), any(), any(), any()) } returns templateId

        //when
        val response = mockMvc.perform(
            post("/api/quizzes/templates")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { apiQuizTemplateService.createNewQuizTemplate(any(), any(), any(), any()) }
        val createQuizTemplateResponse =
            objectMapper.readValue(response.contentAsString, CreateQuizTemplateResponse::class.java)
        assertThat(createQuizTemplateResponse.templateId).isEqualTo(templateId)
    }

    @Test
    fun `should return 400 when create template request is not valid`() {
        //given
        val request = """
                        {
                            "shuffle": {
                                "type": "NONE"
                            },
                            "defaultPassPercentThreshold": 50
                        }
                        """.trimIndent()

        //expect
        mockMvc.perform(
            post("/api/quizzes/templates")
                .contentType("application/json")
                .content(request)
        )
            .andExpect(status().isBadRequest())
    }

}