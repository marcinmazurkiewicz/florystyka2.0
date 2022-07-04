package dev.mazurkiewicz.quizer.question.domain.model;

import dev.mazurkiewicz.quizer.question.application.AnswerStatus;

public record AnswerResult(AnswerStatus status, AnswerType correctAnswer) {
    public boolean isCorrect() {
        return status == AnswerStatus.CORRECT;
    }
}
