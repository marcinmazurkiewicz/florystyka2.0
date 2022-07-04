package dev.mazurkiewicz.quizer.question.domain.model;

public record QuestionId(Integer value) {
    public static QuestionId of(int questionId) {
        return new QuestionId(questionId);
    }
}
