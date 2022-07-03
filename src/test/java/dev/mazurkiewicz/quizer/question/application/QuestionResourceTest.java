package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.TestBeanConfig;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.mazurkiewicz.quizer.config.EndpointProperties.QUESTIONS_ENDPOINT_MAIN;
import static dev.mazurkiewicz.quizer.config.EndpointProperties.QUESTIONS_ENDPOINT_RANDOM;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestBeanConfig.class)
@WebMvcTest(controllers = QuestionResource.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class QuestionResourceTest {

    private static final List<QuestionResponse> questionResponses = new ArrayList<>();
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ApiQuestionService service;

    // questions with an even id have pictures
    @BeforeAll
    private static void prepareQuestionResponses() {
        for (int i = 1; i < 11; i++) {
            questionResponses.add(prepareQuestion(i, i % 2 == 0));
        }
    }

    private static QuestionResponse prepareQuestion(int questionNo, boolean withImg) {
        return new QuestionResponse(questionNo, String.format("Question %d", questionNo),
                Arrays.asList(
                        new AnswerResponse(AnswerType.A, String.format("answer A for question %d", questionNo)),
                        new AnswerResponse(AnswerType.B, String.format("answer B for question %d", questionNo)),
                        new AnswerResponse(AnswerType.C, String.format("answer C for question %d", questionNo)),
                        new AnswerResponse(AnswerType.D, String.format("answer D for question %d", questionNo))),
                (withImg ? String.format("/resources/img/img_%d.png", questionNo) : null)
        );
    }

    @Test
    void shouldReturnRandomQuestionResponseWithImgWhenCallGetRandomQuestion() throws Exception {
        when(service.getRandomQuestion()).thenReturn(questionResponses.get(1));

        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_RANDOM))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.content", is("Question 2")))
                .andExpect(jsonPath("$.answers", hasSize(4)))
                .andExpect(jsonPath("$.answers[0].type", is("A")))
                .andExpect(jsonPath("$.answers[0].content", is("answer A for question 2")))
                .andExpect(jsonPath("$.answers[1].type", is("B")))
                .andExpect(jsonPath("$.answers[1].content", is("answer B for question 2")))
                .andExpect(jsonPath("$.answers[2].type", is("C")))
                .andExpect(jsonPath("$.answers[2].content", is("answer C for question 2")))
                .andExpect(jsonPath("$.answers[3].type", is("D")))
                .andExpect(jsonPath("$.answers[3].content", is("answer D for question 2")))
                .andExpect(jsonPath("$.img", is("/resources/img/img_2.png")));
    }

    @Test
    void shouldReturnRandomQuestionResponseWithoutImgPropertyWhenRandomQuestionImgIsNull() throws Exception {
        when(service.getRandomQuestion()).thenReturn(questionResponses.get(0));

        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_RANDOM))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("Question 1")))
                .andExpect(jsonPath("$.answers", hasSize(4)))
                .andExpect(jsonPath("$.answers[0].type", is("A")))
                .andExpect(jsonPath("$.answers[0].content", is("answer A for question 1")))
                .andExpect(jsonPath("$.answers[1].type", is("B")))
                .andExpect(jsonPath("$.answers[1].content", is("answer B for question 1")))
                .andExpect(jsonPath("$.answers[2].type", is("C")))
                .andExpect(jsonPath("$.answers[2].content", is("answer C for question 1")))
                .andExpect(jsonPath("$.answers[3].type", is("D")))
                .andExpect(jsonPath("$.answers[3].content", is("answer D for question 1")))
                .andExpect(jsonPath("$.img").doesNotExist());
    }

    @Test
    void shouldReturnStatus503WhenServiceThrowEmptyResultException() throws Exception {
        when(service.getRandomQuestion()).thenThrow(EmptyResultDataAccessException.class);

        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_RANDOM))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void shouldReturnQuestionResponseWhenCallGetQuestionById() throws Exception {
        when(service.getQuestionById(2)).thenReturn(questionResponses.get(1));

        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.content", is("Question 2")))
                .andExpect(jsonPath("$.answers", hasSize(4)))
                .andExpect(jsonPath("$.answers[0].type", is("A")))
                .andExpect(jsonPath("$.answers[0].content", is("answer A for question 2")))
                .andExpect(jsonPath("$.answers[1].type", is("B")))
                .andExpect(jsonPath("$.answers[1].content", is("answer B for question 2")))
                .andExpect(jsonPath("$.answers[2].type", is("C")))
                .andExpect(jsonPath("$.answers[2].content", is("answer C for question 2")))
                .andExpect(jsonPath("$.answers[3].type", is("D")))
                .andExpect(jsonPath("$.answers[3].content", is("answer D for question 2")))
                .andExpect(jsonPath("$.img", is("/resources/img/img_2.png")));
    }

    @Test
    void shouldReturnQuestionResponseWithoutImgPropertyWhenQuestionImgIsNull() throws Exception {
        when(service.getQuestionById(1)).thenReturn(questionResponses.get(0));

        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("Question 1")))
                .andExpect(jsonPath("$.answers", hasSize(4)))
                .andExpect(jsonPath("$.answers[0].type", is("A")))
                .andExpect(jsonPath("$.answers[0].content", is("answer A for question 1")))
                .andExpect(jsonPath("$.answers[1].type", is("B")))
                .andExpect(jsonPath("$.answers[1].content", is("answer B for question 1")))
                .andExpect(jsonPath("$.answers[2].type", is("C")))
                .andExpect(jsonPath("$.answers[2].content", is("answer C for question 1")))
                .andExpect(jsonPath("$.answers[3].type", is("D")))
                .andExpect(jsonPath("$.answers[3].content", is("answer D for question 1")))
                .andExpect(jsonPath("$.img").doesNotExist());
    }

    @Test
    void shouldReturnStatus404WhenServiceThrowResourceNotFoundException() throws Exception {
        when(service.getQuestionById(anyInt())).thenThrow(ResourceNotFoundException.class);

        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + "/11"))
                .andExpect(status().isNotFound());
    }


}