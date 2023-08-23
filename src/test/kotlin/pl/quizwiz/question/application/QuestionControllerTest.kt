package pl.quizwiz.question.application

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import pl.quizwiz.auth.prepareAuthentication
import pl.quizwiz.exception.ApiError
import pl.quizwiz.exception.QuizerExceptionHandler
import pl.quizwiz.question.domain.model.AnswerType
import pl.quizwiz.question.infrastructure.QuestionInvalidAnswersStateException
import pl.quizwiz.question.infrastructure.QuestionNotFoundException
import pl.quizwiz.question.infrastructure.QuestionWithoutCorrectAnswerException
import pl.quizwiz.template.infrastructure.IllegalTemplateAccessException
import java.util.*

class QuestionControllerTest {
    private val apiQuestionService = mockk<ApiQuestionService>()
    private val questionController = QuestionController(apiQuestionService)
    private val objectMapper = jacksonObjectMapper()
    private val mockMvc = MockMvcBuilders.standaloneSetup(questionController)
        .setControllerAdvice(QuizerExceptionHandler())
        .setCustomArgumentResolvers(prepareAuthentication())
        .build()

    @Test
    fun `should return question response when question exist`() {
        //given
        val questionIdParam = UUID.randomUUID()

        every { apiQuestionService.getQuestionById(questionIdParam) } returns QuestionResponse(
            questionIdParam,
            "Test question",
            listOf(
                AnswerResponse(AnswerType.A, "answer A"),
                AnswerResponse(AnswerType.B, "answer B"),
                AnswerResponse(AnswerType.C, "answer C"),
                AnswerResponse(AnswerType.D, "answer D")
            )
        )

        //when
        val response = mockMvc.perform(MockMvcRequestBuilders.get("/api/questions/{questionId}", questionIdParam))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { apiQuestionService.getQuestionById(questionIdParam) }
        val questionResponse = objectMapper.readValue(response.contentAsString, QuestionResponse::class.java)
        Assertions.assertThat(questionResponse.id).isEqualTo(questionIdParam)
        Assertions.assertThat(questionResponse.content).isEqualTo("Test question")
        Assertions.assertThat(questionResponse.answers).contains(AnswerResponse(AnswerType.A, "answer A"))
        Assertions.assertThat(questionResponse.answers).contains(AnswerResponse(AnswerType.B, "answer B"))
        Assertions.assertThat(questionResponse.answers).contains(AnswerResponse(AnswerType.C, "answer C"))
        Assertions.assertThat(questionResponse.answers).contains(AnswerResponse(AnswerType.D, "answer D"))
        Assertions.assertThat(questionResponse.image).isNull()
    }

    @Test
    fun `should return 404 response when question does not exist`() {
        //given
        val questionIdParam = UUID.randomUUID()

        every { apiQuestionService.getQuestionById(questionIdParam) } throws QuestionNotFoundException(questionIdParam)

        //when
        val response = mockMvc.perform(MockMvcRequestBuilders.get("/api/questions/{questionId}", questionIdParam))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { apiQuestionService.getQuestionById(any()) }
        val errorResponse = objectMapper.readValue(response.contentAsString, ApiError::class.java)
        Assertions.assertThat(errorResponse.errorCode).isEqualTo("QW_1005")
    }

    @Test
    fun `should save valid question and return question id`() {
        val questionId = UUID.randomUUID()
        val templateId = UUID.randomUUID()
        val content = "Test question"
        val newQuestionRequest = NewQuestionRequest(
            templateId,
            content,
            listOf(
                QuestionAnswer(AnswerType.A, "answer A", false),
                QuestionAnswer(AnswerType.B, "answer B", true),
                QuestionAnswer(AnswerType.C, "answer C", false),
                QuestionAnswer(AnswerType.D, "answer D", false),
            )
        )
        every { apiQuestionService.saveQuestion(templateId, content, any(), any()) } returns questionId


        //when
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/questions")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newQuestionRequest))
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { apiQuestionService.saveQuestion(any(), any(), any(), any()) }
        val questionResponse = objectMapper.readValue(response.contentAsString, NewQuestionResponse::class.java)
        Assertions.assertThat(questionResponse.questionId).isEqualTo(questionId)
    }

    @Test
    fun `should return 400 response when question request does not have correct answer`() {
        //given
        val questionId = UUID.randomUUID()
        val templateId = UUID.randomUUID()
        val content = "Test question"
        val newQuestionRequest = NewQuestionRequest(
            templateId,
            content,
            listOf(
                QuestionAnswer(AnswerType.A, "answer A", false),
                QuestionAnswer(AnswerType.B, "answer B", false),
                QuestionAnswer(AnswerType.C, "answer C", false),
                QuestionAnswer(AnswerType.D, "answer D", false),
            )
        )
        every {
            apiQuestionService.saveQuestion(
                templateId,
                content,
                any(),
                any()
            )
        } throws QuestionWithoutCorrectAnswerException(questionId)

        //when
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/questions")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newQuestionRequest))
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { apiQuestionService.saveQuestion(any(), any(), any(), any()) }
        val errorResponse = objectMapper.readValue(response.contentAsString, ApiError::class.java)
        Assertions.assertThat(errorResponse.errorCode).isEqualTo("QW_1003")
    }

    @Test
    fun `should return 400 response when question request does not have valid answers list`() {
        //given
        val templateId = UUID.randomUUID()
        val content = "Test question"
        val newQuestionRequest = NewQuestionRequest(
            templateId,
            content,
            listOf(
                QuestionAnswer(AnswerType.A, "answer A", false),
                QuestionAnswer(AnswerType.A, "answer A", false),
                QuestionAnswer(AnswerType.C, "answer C", false),
                QuestionAnswer(AnswerType.D, "answer D", false),
            )
        )
        every {
            apiQuestionService.saveQuestion(
                templateId,
                content,
                any(),
                any()
            )
        } throws QuestionInvalidAnswersStateException(AnswerType.A, 2)

        //when
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/questions")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newQuestionRequest))
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { apiQuestionService.saveQuestion(any(), any(), any(), any()) }
        val errorResponse = objectMapper.readValue(response.contentAsString, ApiError::class.java)
        Assertions.assertThat(errorResponse.errorCode).isEqualTo("QW_1004")
    }


    @Test
    fun `should return 401 response when question request has different author than template`() {
        //given
        val templateId = UUID.randomUUID()
        val content = "Test question"
        val newQuestionRequest = NewQuestionRequest(
            templateId,
            content,
            listOf(
                QuestionAnswer(AnswerType.A, "answer A", false),
                QuestionAnswer(AnswerType.A, "answer A", false),
                QuestionAnswer(AnswerType.C, "answer C", false),
                QuestionAnswer(AnswerType.D, "answer D", false),
            )
        )
        every {
            apiQuestionService.saveQuestion(
                templateId,
                content,
                any(),
                any()
            )
        } throws IllegalTemplateAccessException(UUID.randomUUID(), templateId)

        //when
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/questions")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newQuestionRequest))
        )
            .andExpect(MockMvcResultMatchers.status().isForbidden())
            .andReturn()
            .response

        //then
        verify(exactly = 1) { apiQuestionService.saveQuestion(any(), any(), any(), any()) }
        val errorResponse = objectMapper.readValue(response.contentAsString, ApiError::class.java)
        Assertions.assertThat(errorResponse.errorCode).isEqualTo("QW_1002")
    }
}