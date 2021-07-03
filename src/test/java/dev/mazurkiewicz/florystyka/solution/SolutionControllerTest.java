package dev.mazurkiewicz.florystyka.solution;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import dev.mazurkiewicz.florystyka.jwt.JwtTokenUtil;
import dev.mazurkiewicz.florystyka.user.UserService;
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
import static org.mockito.ArgumentMatchers.any;
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

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;


    public static String toJson(final Object request) {
        try {
            return new ObjectMapper().writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckAnswer() throws Exception {
        SolutionRequest request = new SolutionRequest(1, AnswerType.C);
        when(service.checkSingleAnswer(request)).thenReturn(new SolutionResponse(0, 1, Map.of(1L, AnswerType.C)));

        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points", is(0)))
                .andExpect(jsonPath("$.total", is(1)))
                .andExpect(jsonPath("$.solutions.1", is("C")));
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckAnswerWithEmptySelectedAnswer() throws Exception {
        when(service.checkSingleAnswer(any(SolutionRequest.class))).thenReturn(new SolutionResponse(0, 1, Map.of(1L, AnswerType.B)));

        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"questionId\":\"1\",\"selectedAnswer\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points", is(0)))
                .andExpect(jsonPath("$.total", is(1)))
                .andExpect(jsonPath("$.solutions.1", is("B")));
    }

    @Test
    void shouldReturnStatus400WhenCallCheckAnswerWithIllegalRequestBody() throws Exception {
        SolutionRequest request = new SolutionRequest(1, AnswerType.C);
        when(service.checkSingleAnswer(request)).thenReturn(new SolutionResponse(0, 1, Map.of(1L, AnswerType.C)));

        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"selectedAnswer\":\"C\""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatus404WhenCallCheckAnswerWithNonExistQuestionId() throws Exception {
        SolutionRequest request = new SolutionRequest(11, AnswerType.C);
        when(service.checkSingleAnswer(request)).thenThrow(ResourceNotFoundException.class);

        mvc.perform(post("/api/v3/solutions/single")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnTestQuestionResponseWhenCallCheckTestWithValidBodyRequest() throws Exception {
        List<SolutionRequest> request = Arrays.asList(
                new SolutionRequest(1, AnswerType.A),
                new SolutionRequest(2, AnswerType.B),
                new SolutionRequest(3, AnswerType.C),
                new SolutionRequest(4, AnswerType.D),
                new SolutionRequest(5, AnswerType.EMPTY)
        );
        Map<Long, AnswerType> responseSolutions = new HashMap<>();
        responseSolutions.put(1L, AnswerType.B);
        responseSolutions.put(2L, AnswerType.B);
        responseSolutions.put(3L, AnswerType.B);
        responseSolutions.put(4L, AnswerType.B);
        responseSolutions.put(5L, AnswerType.B);
        when(service.checkTest(request)).thenReturn(new SolutionResponse(1, 5, responseSolutions));

        mvc.perform(post("/api/v3/solutions/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points", is(1)))
                .andExpect(jsonPath("$.total", is(5)))
                .andExpect(jsonPath("$.solutions.1", is("B")))
                .andExpect(jsonPath("$.solutions.2", is("B")))
                .andExpect(jsonPath("$.solutions.3", is("B")))
                .andExpect(jsonPath("$.solutions.4", is("B")))
                .andExpect(jsonPath("$.solutions.5", is("B")));
    }

    @Test
    void shouldReturnStatus400WhenCallCheckTestWithIllegalRequestBody() throws Exception {
        SolutionRequest request = new SolutionRequest(1, AnswerType.C);
        when(service.checkSingleAnswer(request)).thenReturn(new SolutionResponse(0, 1, Map.of(1L, AnswerType.C)));

        mvc.perform(post("/api/v3/solutions/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"selectedAnswer\":\"C\"},{\\questionId\": 1, \"selectedAnswer\":\"B\"}]"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnStatus404WhenCallCheckAnswerWithNonExistQuestionId2() throws Exception {
        List<SolutionRequest> request = Arrays.asList(
                new SolutionRequest(1, AnswerType.A),
                new SolutionRequest(2, AnswerType.B),
                new SolutionRequest(3, AnswerType.C),
                new SolutionRequest(4, AnswerType.D),
                new SolutionRequest(5, AnswerType.EMPTY)
        );
        when(service.checkTest(request)).thenThrow(ResourceNotFoundException.class);

        mvc.perform(post("/api/v3/solutions/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isNotFound());
    }
}