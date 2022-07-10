package dev.mazurkiewicz.quizer.exam.application;

import dev.mazurkiewicz.quizer.exam.domain.model.ExamCorrectAnswers;
import dev.mazurkiewicz.quizer.exam.domain.model.ExamResult;
import dev.mazurkiewicz.quizer.exam.domain.model.ExamStatus;
import dev.mazurkiewicz.quizer.exam.domain.model.Points;
import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;

import java.util.Map;
import java.util.stream.Collectors;

public record ExamAnswerResponse(Points points, ExamStatus status, Map<Integer, AnswerType> correctAnswers) {
    public static ExamAnswerResponse of(ExamResult examResult, ExamCorrectAnswers examCorrectAnswers) {
        Map<Integer, AnswerType> correctAnswers = examCorrectAnswers.examCorrectAnswers()
                .stream()
                .collect(Collectors.toMap(k -> k.id().value(), v -> v.correctAnswer().type()));
        return new ExamAnswerResponse(examResult.points(), examResult.status(), correctAnswers);
    }
}
