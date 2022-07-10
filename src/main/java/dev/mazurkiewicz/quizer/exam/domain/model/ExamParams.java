package dev.mazurkiewicz.quizer.exam.domain.model;

import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;

import java.util.List;

public record ExamParams(ExamDuration examDuration, ExamPassThreshold passThreshold, List<QuestionId> questionIds) {
}
