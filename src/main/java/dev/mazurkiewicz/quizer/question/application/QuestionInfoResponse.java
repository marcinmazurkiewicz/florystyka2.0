package dev.mazurkiewicz.quizer.question.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record QuestionInfoResponse(long questionNumber, Integer earliestYear, Integer latestYear) {

    public static QuestionInfoResponse of(QuestionInfo questionInfo) {
        return new QuestionInfoResponse(questionInfo.questionNumber(), questionInfo.earliestQuestionYear(), questionInfo.latestQuestionYear());
    }
}
