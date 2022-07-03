package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.TestBeanConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static dev.mazurkiewicz.quizer.config.EndpointProperties.QUESTIONS_INFO_ENDPOINT_MAIN;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestBeanConfig.class)
@WebMvcTest(controllers = QuestionInfoResource.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class QuestionInfoResourceTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ApiQuestionService service;

    @Test
    void shouldReturnQuestionNumberAndEarlierYearAndLatestYear() throws Exception {
        when(service.getQuestionsInfo()).thenReturn(new QuestionInfoResponse(10L, 2000, 2020));

        mvc.perform(get(QUESTIONS_INFO_ENDPOINT_MAIN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionNumber", is(10)))
                .andExpect(jsonPath("$.earliestYear", is(2000)))
                .andExpect(jsonPath("$.latestYear", is(2020)));
    }

    @Test
    void shouldReturnQuestionNumber() throws Exception {
        when(service.getQuestionsInfo()).thenReturn(new QuestionInfoResponse(10L, null, null));

        mvc.perform(get(QUESTIONS_INFO_ENDPOINT_MAIN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionNumber", is(10)))
                .andExpect(jsonPath("$.earliestYear").doesNotExist())
                .andExpect(jsonPath("$.latestYear").doesNotExist());
    }
}