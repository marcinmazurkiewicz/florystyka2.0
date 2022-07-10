package dev.mazurkiewicz.quizer.exam.domain.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExamSolution {
    private final ExamSelectedAnswers selectedAnswers;

    public AchievedPoints calculatePoints() {
        return selectedAnswers.countCorrectAnswers();
    }
}
