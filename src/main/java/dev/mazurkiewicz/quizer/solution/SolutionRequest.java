package dev.mazurkiewicz.quizer.solution;

import jakarta.validation.constraints.Min;

public record SolutionRequest(@Min(1) int questionId, String selectedAnswer) {

}
