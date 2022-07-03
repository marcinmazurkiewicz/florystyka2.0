package dev.mazurkiewicz.quizer.question.domain.model;

public record Answer(AnswerType type, AnswerContent content, boolean isCorrect) {

}
