package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;

public record AnswerStatusResponse(Integer questionId, AnswerStatus status, AnswerType correctAnswer) {
}
