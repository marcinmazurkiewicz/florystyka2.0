//package dev.mazurkiewicz.quizer.question.application;
//
//import dev.mazurkiewicz.quizer.exception.PdfRenderException;
//import dev.mazurkiewicz.quizer.exam.application.ExamResponse;
//import dev.mazurkiewicz.quizer.exam.application.ExamTimer;
//import org.junit.jupiter.api.Test;
//import org.springframework.dao.IncorrectResultSizeDataAccessException;
//import org.springframework.http.MediaType;
//
//import static dev.mazurkiewicz.quizer.config.EndpointProperties.*;
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class QuestionTest {
//    @Test
//    void shouldReturnQuestionNumberResponse() throws Exception {
//        when(service.getQuestionsInfo())
//                .thenReturn(new QuestionInfoResponse(100L, 2010, 2020));
//
//        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_INFO))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.questionNumber", is(100)))
//                .andExpect(jsonPath("$.earliestQuestionYear", is(2010)))
//                .andExpect(jsonPath("$.latestQuestionYear", is(2020)));
//    }
//
//    @Test
//    void shouldReturnQuestionNumberResponseWhenYearAreNulls() throws Exception {
//        when(service.getQuestionsInfo())
//                .thenReturn(new QuestionInfoResponse(0, null, null));
//
//        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_INFO))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.questionNumber", is(0)))
//                .andExpect(jsonPath("$.earliestQuestionYear").doesNotExist())
//                .andExpect(jsonPath("$.latestQuestionYear").doesNotExist());
//    }
//
//
//    @Test
//    void shouldReturnListOfQuestionResponseWhenCallGetQuestionToTest() throws Exception {
//        when(service.getExamData()).thenReturn(new ExamResponse(new ExamTimer(10, 0), questionResponses));
//
//        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_EXAM))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.timer.minutes", is(10)))
//                .andExpect(jsonPath("$.timer.seconds", is(0)))
//                .andExpect(jsonPath("$.questions", hasSize(10)))
//                .andExpect(jsonPath("$.questions[0].content", is("Question 1")))
//                .andExpect(jsonPath("$.questions[0].answers", hasSize(4)))
//                .andExpect(jsonPath("$.questions[0].answers[0].value", is("A")))
//                .andExpect(jsonPath("$.questions[0].answers[0].content", is("answer A for question 1")))
//                .andExpect(jsonPath("$.questions[0].answers[1].value", is("B")))
//                .andExpect(jsonPath("$.questions[0].answers[1].content", is("answer B for question 1")))
//                .andExpect(jsonPath("$.questions[0].answers[2].value", is("C")))
//                .andExpect(jsonPath("$.questions[0].answers[2].content", is("answer C for question 1")))
//                .andExpect(jsonPath("$.questions[0].answers[3].value", is("D")))
//                .andExpect(jsonPath("$.questions[0].answers[3].content", is("answer D for question 1")))
//                .andExpect(jsonPath("$.questions[0].img").doesNotExist())
//                .andExpect(jsonPath("$.questions[9].id", is(10)))
//                .andExpect(jsonPath("$.questions[9].content", is("Question 10")))
//                .andExpect(jsonPath("$.questions[9].answers", hasSize(4)))
//                .andExpect(jsonPath("$.questions[9].answers[0].value", is("A")))
//                .andExpect(jsonPath("$.questions[9].answers[0].content", is("answer A for question 10")))
//                .andExpect(jsonPath("$.questions[9].answers[1].value", is("B")))
//                .andExpect(jsonPath("$.questions[9].answers[1].content", is("answer B for question 10")))
//                .andExpect(jsonPath("$.questions[9].answers[2].value", is("C")))
//                .andExpect(jsonPath("$.questions[9].answers[2].content", is("answer C for question 10")))
//                .andExpect(jsonPath("$.questions[9].answers[3].value", is("D")))
//                .andExpect(jsonPath("$.questions[9].answers[3].content", is("answer D for question 10")))
//                .andExpect(jsonPath("$.questions[9].img", is("/resources/img/img_10.png")));
//    }
//
//    @Test
//    void shouldReturnStatus503WhenServiceThrowIncorrectResultSizeException() throws Exception {
//        when(service.getExamData()).thenThrow(IncorrectResultSizeDataAccessException.class);
//
//        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_EXAM))
//                .andExpect(status().isServiceUnavailable());
//    }
//
//    @Test
//    void shouldReturnByteArrayWhenCallGetPdfTest() throws Exception {
//        byte[] returnedBytesArray = "mock content".getBytes();
//        when(service.getPdfTest()).thenReturn(returnedBytesArray);
//
//        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_PDF))
//                .andExpect(status().isOk())
//                .andExpect(header().string("Content-Type", MediaType.APPLICATION_PDF_VALUE))
//                .andExpect(header().string("Content-Length", String.valueOf(returnedBytesArray.length)))
//                .andExpect(header().string("Content-Disposition", "attachment; filename*=UTF-8''" + properties.pdfName()))
//                .andExpect(content().bytes(returnedBytesArray));
//    }
//
//    @Test
//    void shouldReturnStatus503WhenServiceThrownPdfRenderException() throws Exception {
//        when(service.getPdfTest()).thenThrow(PdfRenderException.class);
//
//        mvc.perform(get(QUESTIONS_ENDPOINT_MAIN + QUESTIONS_ENDPOINT_PDF))
//                .andExpect(status().isInternalServerError());
//    }
//}
