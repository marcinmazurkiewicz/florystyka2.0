package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.config.EndpointProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointProperties.QUESTIONS_INFO_ENDPOINT_MAIN)
class QuestionInfoResource {

    private final ApiQuestionService service;

    @GetMapping
    QuestionInfoResponse getQuestionNumber() {
        return service.getQuestionsInfo();
    }
}
