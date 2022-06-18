package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.questions.AnswerType;

import java.util.Map;

public record AnswerResponse(Integer points, Integer total, Map<Integer, AnswerType> correctAnswers) {
}
