//package dev.mazurkiewicz.quizer.solution;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
//import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static dev.mazurkiewicz.quizer.config.EndpointProperties.*;
//import static org.hamcrest.Matchers.aMapWithSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = SolutionController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
//class SolutionControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private SolutionService service;
//
//    public static String toJson(final Object request) {
//        try {
//            return new ObjectMapper().writeValueAsString(request);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void shouldReturnSolutionResponseWhenCallCheckAnswer() throws Exception {
//        AnswerRequest request = new AnswerRequest(1, "C");
//        when(service.checkSingle(request)).thenReturn(new AnswerResponse(0, 1, Map.of(1, AnswerType.B)));
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_SINGLE)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.points", is(0)))
//                .andExpect(jsonPath("$.total", is(1)))
//                .andExpect(jsonPath("$.correctAnswers", aMapWithSize(1)))
//                .andExpect(jsonPath("$.correctAnswers.1", is("B")));
//    }
//
//    @Test
//    void shouldReturnSolutionResponseWhenCallCheckAnswerWithEmptySelectedAnswer() throws Exception {
//        AnswerRequest request = new AnswerRequest(1, "");
//        when(service.checkSingle(request)).thenReturn(new AnswerResponse(0, 1, Map.of(1, AnswerType.B)));
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_SINGLE)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.points", is(0)))
//                .andExpect(jsonPath("$.total", is(1)))
//                .andExpect(jsonPath("$.correctAnswers", aMapWithSize(1)));
//    }
//
//    @Test
//    void shouldReturnStatus400WhenCallCheckAnswerWithIllegalRequestBody() throws Exception {
//        AnswerRequest request = new AnswerRequest(1, "C");
//        when(service.checkSingle(request)).thenReturn(new AnswerResponse(0, 1, Map.of(1, AnswerType.B)));
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_SINGLE)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("\"selectedAnswers\":\"C\""))
//                .andExpect(status().isBadRequest());
//    }
//
//
//    @Test
//    void shouldReturnStatus400WhenCallCheckAnswerWithIllegalAnswerType() throws Exception {
//        AnswerRequest request = new AnswerRequest(1, "f");
//        when(service.checkSingle(request)).thenReturn(new AnswerResponse(0, 1, Map.of(1, AnswerType.B)));
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_SINGLE)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(request)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnStatus404WhenCallCheckAnswerWithNonExistQuestionId() throws Exception {
//        AnswerRequest request = new AnswerRequest(11, "C");
//        when(service.checkSingle(request)).thenThrow(ResourceNotFoundException.class);
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_SINGLE)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(request)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void shouldReturnTestQuestionResponseWhenCallCheckTestWithValidBodyRequest() throws Exception {
//        List<AnswerRequest> request = Arrays.asList(
//                new AnswerRequest(1, "A"),
//                new AnswerRequest(2, "B"),
//                new AnswerRequest(3, "C"),
//                new AnswerRequest(4, "D"),
//                new AnswerRequest(5, "")
//        );
//        Map<Integer, AnswerType> responseSolutions = new HashMap<>();
//        responseSolutions.put(1, AnswerType.B);
//        responseSolutions.put(2, AnswerType.B);
//        responseSolutions.put(3, AnswerType.B);
//        responseSolutions.put(4, AnswerType.B);
//        responseSolutions.put(5, AnswerType.B);
//        when(service.checkTest(request)).thenReturn(new AnswerResponse(1, 5, responseSolutions));
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_EXAM)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.points", is(1)))
//                .andExpect(jsonPath("$.total", is(5)))
//                .andExpect(jsonPath("$.correctAnswers.1", is("B")))
//                .andExpect(jsonPath("$.correctAnswers.2", is("B")))
//                .andExpect(jsonPath("$.correctAnswers.3", is("B")))
//                .andExpect(jsonPath("$.correctAnswers.4", is("B")))
//                .andExpect(jsonPath("$.correctAnswers.5", is("B")));
//    }
//
//    @Test
//    void shouldReturnStatus400WhenCallCheckTestWithIllegalRequestBody() throws Exception {
//        AnswerRequest request = new AnswerRequest(1, "C");
//        when(service.checkSingle(request)).thenReturn(new AnswerResponse(0, 1, Map.of(1, AnswerType.B)));
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_EXAM)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("[{\"selectedAnswers\":\"C\"},{\\questionId\": 1, \"selectedAnswers\":\"B\"}]"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnStatus404WhenCallCheckAnswerWithNonExistQuestionId2() throws Exception {
//        List<AnswerRequest> request = Arrays.asList(
//                new AnswerRequest(1, "A"),
//                new AnswerRequest(2, "B"),
//                new AnswerRequest(3, "C"),
//                new AnswerRequest(4, "D"),
//                new AnswerRequest(5, "")
//        );
//        when(service.checkTest(request)).thenThrow(ResourceNotFoundException.class);
//
//        mvc.perform(post(SOLUTIONS_ENDPOINT_MAIN + SOLUTIONS_ENDPOINT_EXAM)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(request)))
//                .andExpect(status().isNotFound());
//    }
//}