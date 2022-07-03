package dev.mazurkiewicz.quizer.question.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.quizer.question.domain.model.Question;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record QuestionResponse(Integer id, String content, List<AnswerResponse> answers, String img) {

    static QuestionResponse of(Question question) {
        List<AnswerResponse> answerResponses = question.getAnswers()
                .stream()
                .map(AnswerResponse::of)
                .toList();

        return new QuestionResponse(question.getId().value(),
                question.getContent().value(),
                answerResponses,
                question.getImage().path());
    }
}
