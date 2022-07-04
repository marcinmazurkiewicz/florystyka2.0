package dev.mazurkiewicz.quizer.question.application;

public enum AnswerStatus {
    CORRECT, INCORRECT;

    public static AnswerStatus of(Boolean isCorrect) {
        return isCorrect ? CORRECT : INCORRECT;
    }
}
