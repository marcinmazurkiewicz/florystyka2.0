package dev.mazurkiewicz.florystyka.solution;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SolutionController.class)
class SolutionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SolutionService service;

    public static String toJson(final Object request) {
        try {
            return new ObjectMapper().writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckAnswer() throws Exception {
        //given
        SolutionRequest request = new SolutionRequest(1, "C");
        when(service.checkSingleAnswer(request)).thenReturn(new SolutionResponse(1, AnswerType.B, AnswerType.C));
        //when
        //then
        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId", is(1)))
                .andExpect(jsonPath("$.correct", is("B")))
                .andExpect(jsonPath("$.selected", is("C")));
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckAnswerWithEmptySelectedAnswer() throws Exception {
        //given
        SolutionRequest request = new SolutionRequest(1, "");
        when(service.checkSingleAnswer(request)).thenReturn(new SolutionResponse(1, AnswerType.B, AnswerType.EMPTY));
        //when
        //then
        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId", is(1)))
                .andExpect(jsonPath("$.correct", is("B")))
                .andExpect(jsonPath("$.selected", is("EMPTY")));
    }

    @Test
    void shouldReturnStatus400WhenCallCheckAnswerWithIllegalRequestBody() throws Exception {
        //given
        SolutionRequest request = new SolutionRequest(1, "C");
        when(service.checkSingleAnswer(request)).thenReturn(new SolutionResponse(1, AnswerType.B, AnswerType.C));
        //when
        //then
        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"selectedAnswer\":\"C\""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatus404WhenCallCheckAnswerWithNonExistQuestionId() throws Exception {
        //given
        SolutionRequest request = new SolutionRequest(11, "C");
        when(service.checkSingleAnswer(request)).thenThrow(ResourceNotFoundException.class);
        //when
        //then
        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnTestQuestionResponseWhenCallCheckTestWithValidBodyRequest() throws Exception {
        //given
        List<SolutionRequest> request = Arrays.asList(
                new SolutionRequest(1, "A"),
                new SolutionRequest(2, "B"),
                new SolutionRequest(3, "C"),
                new SolutionRequest(4, "D"),
                new SolutionRequest(5, "")
        );
        Map<Integer, SolutionResponse> responseSolutions = new HashMap<>();
        responseSolutions.put(1, new SolutionResponse(1, AnswerType.B, AnswerType.A));
        responseSolutions.put(2, new SolutionResponse(2, AnswerType.B, AnswerType.B));
        responseSolutions.put(3, new SolutionResponse(3, AnswerType.B, AnswerType.C));
        responseSolutions.put(4, new SolutionResponse(4, AnswerType.B, AnswerType.D));
        responseSolutions.put(5, new SolutionResponse(5, AnswerType.B, AnswerType.EMPTY));
        when(service.checkTest(request)).thenReturn(new TestSolutionResponse(1, 5, responseSolutions));

        //when
        //then
        mvc.perform(post("/api/v3/solutions/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points", is(1)))
                .andExpect(jsonPath("$.total", is(5)))
                .andExpect(jsonPath("$.solutions.1.questionId", is(1)))
                .andExpect(jsonPath("$.solutions.1.correct", is("B")))
                .andExpect(jsonPath("$.solutions.1.selected", is("A")))
                .andExpect(jsonPath("$.solutions.2.questionId", is(2)))
                .andExpect(jsonPath("$.solutions.2.correct", is("B")))
                .andExpect(jsonPath("$.solutions.2.selected", is("B")))
                .andExpect(jsonPath("$.solutions.3.questionId", is(3)))
                .andExpect(jsonPath("$.solutions.3.correct", is("B")))
                .andExpect(jsonPath("$.solutions.3.selected", is("C")))
                .andExpect(jsonPath("$.solutions.4.questionId", is(4)))
                .andExpect(jsonPath("$.solutions.4.correct", is("B")))
                .andExpect(jsonPath("$.solutions.4.selected", is("D")))
                .andExpect(jsonPath("$.solutions.5.questionId", is(5)))
                .andExpect(jsonPath("$.solutions.5.correct", is("B")))
                .andExpect(jsonPath("$.solutions.5.selected", is("EMPTY")));
    }

    @Test
    void shouldReturnStatus400WhenCallCheckTestWithIllegalRequestBody() throws Exception {
        //given
        SolutionRequest request = new SolutionRequest(1, "C");
        when(service.checkSingleAnswer(request)).thenReturn(new SolutionResponse(1, AnswerType.B, AnswerType.C));
        //when
        //then
        mvc.perform(post("/api/v3/solutions/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"selectedAnswer\":\"C\"},{\\questionId\": 1, \"selectedAnswer\":\"B\"}]"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatus404WhenCallCheckAnswerWithNonExistQuestionId2() throws Exception {
        //given
        List<SolutionRequest> request = Arrays.asList(
                new SolutionRequest(1, "A"),
                new SolutionRequest(2, "B"),
                new SolutionRequest(3, "C"),
                new SolutionRequest(4, "D"),
                new SolutionRequest(5, "")
        );
        when(service.checkTest(request)).thenThrow(ResourceNotFoundException.class);
        //when
        //then
        mvc.perform(post("/api/v3/solutions/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isNotFound());
    }
}