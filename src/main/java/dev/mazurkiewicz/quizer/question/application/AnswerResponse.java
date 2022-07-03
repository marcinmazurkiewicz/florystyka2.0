package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.question.domain.model.Answer;
import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;

record AnswerResponse(AnswerType type, String content) {

    static AnswerResponse of(Answer answer) {
        return new AnswerResponse(answer.type(), answer.content().value());
    }
}
