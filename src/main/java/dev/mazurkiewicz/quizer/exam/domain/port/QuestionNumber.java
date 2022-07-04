package dev.mazurkiewicz.quizer.exam.domain.port;

public record QuestionNumber(int value) {
    public static QuestionNumber of(Integer examQuestionsNumber) {
        return new QuestionNumber(examQuestionsNumber);
    }
}
