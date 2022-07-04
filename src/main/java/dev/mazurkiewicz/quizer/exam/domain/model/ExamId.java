package dev.mazurkiewicz.quizer.exam.domain.model;

public record ExamId(String value) {
    public static ExamId of(String encodedQuestionIds) {
        return new ExamId(encodedQuestionIds);
    }
}
