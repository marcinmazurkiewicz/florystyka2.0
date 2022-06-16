package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.questions.AnswerType;

public record SolutionResponse(Integer questionId, AnswerType correct, AnswerType selected) {

}
