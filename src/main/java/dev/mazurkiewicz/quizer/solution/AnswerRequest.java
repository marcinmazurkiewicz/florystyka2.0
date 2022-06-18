package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.questions.AnswerType;
import jakarta.validation.constraints.Min;

public record AnswerRequest(@Min(1) int questionId, @ValidAnswerType String selected) {

    AnswerType selectedAnswer() {
        return AnswerType.of(selected);
    }
}
