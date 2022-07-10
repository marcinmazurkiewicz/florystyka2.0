package dev.mazurkiewicz.quizer.exam.domain.model;

import dev.mazurkiewicz.quizer.question.domain.model.Answer;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;

public record ExamCorrectAnswer(QuestionId id, Answer correctAnswer) {

}
