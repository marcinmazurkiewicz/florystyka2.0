package dev.mazurkiewicz.quizer.question.domain.model;

import dev.mazurkiewicz.quizer.question.application.AnswerStatus;

public record Answer(AnswerType type, AnswerContent content, AnswerStatus status) {

}
