package dev.mazurkiewicz.quizer.exam.domain.model;

import dev.mazurkiewicz.quizer.question.domain.model.AnswerResult;
import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;
import dev.mazurkiewicz.quizer.question.domain.model.Question;
import dev.mazurkiewicz.quizer.question.domain.model.SelectedAnswer;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ExamSelectedAnswers {

    private final Map<Question, AnswerType> answers;

    public static ExamSelectedAnswers of(Map<Question, AnswerType> examSelectedAnswerMap) {
        return new ExamSelectedAnswers(examSelectedAnswerMap);
    }

    public AchievedPoints countCorrectAnswers() {
        int achievedPoints = (int) answers.entrySet()
                .stream()
                .map(solution -> solution.getKey().checkAnswer(SelectedAnswer.of(solution.getValue())))
                .filter(AnswerResult::isCorrect)
                .count();
        return AchievedPoints.of(achievedPoints);
    }
}
