package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;

import java.util.Map;

public record AnswerResponse(Integer points, Integer total, Map<Integer, AnswerType> correctAnswers) {
}
