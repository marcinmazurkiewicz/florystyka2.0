package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;
import jakarta.validation.constraints.Min;

public record SelectedAnswerRequest(@Min(1) int questionId, @ValidAnswerType String selected) {

    AnswerType selectedAnswer() {
        return AnswerType.of(selected);
    }
}