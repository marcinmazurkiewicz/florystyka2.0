package dev.mazurkiewicz.quizer.exam.application;

import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;
import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record ExamSolutionRequest(@NotBlank String examId, Map<Integer, AnswerType> selectedAnswers) {
}
