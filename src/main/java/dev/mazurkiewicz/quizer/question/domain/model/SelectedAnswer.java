package dev.mazurkiewicz.quizer.question.domain.model;

public record SelectedAnswer(AnswerType value) {
    public static SelectedAnswer of(AnswerType value) {
        return new SelectedAnswer(value);
    }
}
