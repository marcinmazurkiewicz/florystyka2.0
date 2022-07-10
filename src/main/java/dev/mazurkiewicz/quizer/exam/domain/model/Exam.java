package dev.mazurkiewicz.quizer.exam.domain.model;

import dev.mazurkiewicz.quizer.question.domain.model.Question;

import java.util.List;
import java.util.stream.Collectors;

public class Exam {
    private final ExamId examId;
    private final List<Question> questions;
    private final ExamDuration duration;
    private final ExamPassThreshold passThreshold;
    private final AvailablePoints availablePoints;

    public Exam(List<Question> questions, ExamDuration duration, ExamPassThreshold passThreshold) {
        this.questions = questions;
        this.duration = duration;
        this.passThreshold = passThreshold;
        this.availablePoints = AvailablePoints.of(questions.size());
        this.examId = prepareExamId();
    }

    public static Exam recreate(ExamDuration examDuration, ExamPassThreshold passThreshold, List<Question> questions) {
        return new Exam(questions, examDuration, passThreshold);
    }

    private ExamId prepareExamId() {
        String joinedQuestionIds = questions.stream()
                .map(question -> question.getId().value().toString())
                .collect(Collectors.joining(ExamId.QUESTION_SPLITTER));
        return ExamId.encode(joinedQuestionIds, duration.toSeconds(), passThreshold.neededPoints());
    }

    public ExamResult checkExamSolution(ExamSolution solution) {
        AchievedPoints achievedPoints = solution.calculatePoints();
        ExamStatus status = achievedPoints.value() < passThreshold.neededPoints()
                ? ExamStatus.FAILED
                : ExamStatus.PASSED;

        return new ExamResult(new Points(achievedPoints, availablePoints), status);
    }

    public ExamCorrectAnswers getCorrectAnswers() {
        List<ExamCorrectAnswer> examCorrectAnswers = questions.stream()
                .map(question -> new ExamCorrectAnswer(question.getId(), question.correctAnswer()))
                .toList();
        return new ExamCorrectAnswers(examCorrectAnswers);
    }

    public ExamId examId() {
        return examId;
    }

    public List<Question> questions() {
        return questions;
    }

    public ExamDuration duration() {
        return duration;
    }

    public ExamPassThreshold passThreshold() {
        return passThreshold;
    }
}
