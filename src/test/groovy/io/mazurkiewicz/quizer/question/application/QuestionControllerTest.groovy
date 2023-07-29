package io.mazurkiewicz.quizer.question.application

import com.fasterxml.jackson.databind.ObjectMapper
import io.mazurkiewicz.quizer.question.domain.model.AnswerStatus
import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import io.mazurkiewicz.quizer.question.infrastructure.QuestionNotFoundException
import io.mazurkiewicz.quizer.question.infrastructure.ResourceNotFoundException
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static com.fasterxml.jackson.module.kotlin.ExtensionsKt.jacksonObjectMapper
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class QuestionControllerTest extends Specification {

    private ApiQuestionService apiQuestionService = Mock()
    private QuestionController questionController = new QuestionController(apiQuestionService)
    private ObjectMapper objectMapper = jacksonObjectMapper()
    private MockMvc mockMvc

    void setup() {
        mockMvc = standaloneSetup(questionController).build()
    }

    def 'should return random question with image'() {
        given:
        def questionPublicId = UUID.randomUUID()
        def questionContent = "Question 2"
        def questionImage = "/resources/img/img_2.png"

        when:
        def response = mockMvc.perform(get("/api/questions/random"))
                .andExpect(status().isOk())
                .andReturn()
                .response

        then:
        1 * apiQuestionService.getRandomQuestion() >> new QuestionResponse(
                questionPublicId,
                questionContent,
                [
                        new AnswerResponse(AnswerType.A, "answer A for question 2"),
                        new AnswerResponse(AnswerType.B, "answer B for question 2"),
                        new AnswerResponse(AnswerType.C, "answer C for question 2"),
                        new AnswerResponse(AnswerType.D, "answer D for question 2"),
                ],
                questionImage
        )

        def randomQuestion = objectMapper.readValue(response.contentAsString, QuestionResponse.class)
        randomQuestion.id == questionPublicId
        randomQuestion.content == questionContent
        randomQuestion.answers.size() == 4
        randomQuestion.answers.find { it.type == AnswerType.A }.content == "answer A for question 2"
        randomQuestion.answers.find { it.type == AnswerType.B }.content == "answer B for question 2"
        randomQuestion.answers.find { it.type == AnswerType.C }.content == "answer C for question 2"
        randomQuestion.answers.find { it.type == AnswerType.D }.content == "answer D for question 2"
        randomQuestion.image == questionImage
    }

    def 'should return random question with empty image'() {
        given:
        def questionPublicId = UUID.randomUUID()
        def questionContent = "Question 1"

        when:
        def response = mockMvc.perform(get("/api/questions/random"))
                .andExpect(status().isOk())
                .andReturn()
                .response

        then:
        1 * apiQuestionService.getRandomQuestion() >> new QuestionResponse(
                questionPublicId,
                questionContent,
                [
                        new AnswerResponse(AnswerType.A, "answer A for question 1"),
                        new AnswerResponse(AnswerType.B, "answer B for question 1"),
                        new AnswerResponse(AnswerType.C, "answer C for question 1"),
                        new AnswerResponse(AnswerType.D, "answer D for question 1"),
                ],
                null
        )

        def randomQuestion = objectMapper.readValue(response.contentAsString, QuestionResponse.class)
        randomQuestion.id == questionPublicId
        randomQuestion.content == questionContent
        randomQuestion.answers.size() == 4
        randomQuestion.answers.find { it.type == AnswerType.A }.content == "answer A for question 1"
        randomQuestion.answers.find { it.type == AnswerType.B }.content == "answer B for question 1"
        randomQuestion.answers.find { it.type == AnswerType.C }.content == "answer C for question 1"
        randomQuestion.answers.find { it.type == AnswerType.D }.content == "answer D for question 1"
        randomQuestion.image == null
    }

    def 'should return question with id'() {
        given:
        def questionPublicId = UUID.randomUUID()

        when:
        def response = mockMvc.perform(get("/api/questions/" + questionPublicId.toString()))
                .andExpect(status().isOk())
                .andReturn()
                .response

        then:
        1 * apiQuestionService.getQuestionById(questionPublicId) >> new QuestionResponse(
                questionPublicId,
                "Question 1",
                [
                        new AnswerResponse(AnswerType.A, "answer A for question 1"),
                        new AnswerResponse(AnswerType.B, "answer B for question 1"),
                        new AnswerResponse(AnswerType.C, "answer C for question 1"),
                        new AnswerResponse(AnswerType.D, "answer D for question 1"),
                ],
                null
        )

        def randomQuestion = objectMapper.readValue(response.contentAsString, QuestionResponse.class)
        randomQuestion.id == questionPublicId
        randomQuestion.content == "Question 1"
        randomQuestion.answers.size() == 4
        randomQuestion.answers.find { it.type == AnswerType.A }.content == "answer A for question 1"
        randomQuestion.answers.find { it.type == AnswerType.B }.content == "answer B for question 1"
        randomQuestion.answers.find { it.type == AnswerType.C }.content == "answer C for question 1"
        randomQuestion.answers.find { it.type == AnswerType.D }.content == "answer D for question 1"
        randomQuestion.image == null
    }

    def 'should return 404 when question with id does not exist'() {
        given:
        def questionPublicId = UUID.randomUUID()
        apiQuestionService.getQuestionById(questionPublicId) >> {
            throw new QuestionNotFoundException(questionPublicId)
        }

        expect:
        mockMvc.perform(get("/api/questions/" + questionPublicId.toString()))
                .andExpect(status().isNotFound())
    }

    def 'should return checked answer'() {
        given:
        def questionPublicId = UUID.randomUUID()
        def request = new SelectedAnswerRequest('A')

        when:
        def response = mockMvc.perform(
                post("/api/questions/%s/answer".formatted(questionPublicId.toString()))
                .contentType('application/json')
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .response

        then:
        1 * apiQuestionService.checkAnswer(questionPublicId, request.selectedAnswer) >> new AnswerStatusResponse(questionPublicId, AnswerStatus.INCORRECT, AnswerType.A)

        def answerStatus = objectMapper.readValue(response.contentAsString, AnswerStatusResponse.class)
        answerStatus.questionId == questionPublicId
        answerStatus.status == AnswerStatus.INCORRECT
        answerStatus.correctAnswer == AnswerType.A
    }
}
