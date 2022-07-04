package dev.mazurkiewicz.quizer.exam.domain.model;

import dev.mazurkiewicz.quizer.question.domain.model.Question;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
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

    private ExamId prepareExamId() {
        String joinedQuestionIds = questions.stream()
                .map(question -> question.getId().value().toString())
                .collect(Collectors.joining("-"));
        String encodedQuestionIds = encodeQuestionIds(joinedQuestionIds);
        return ExamId.of(encodedQuestionIds);
    }

    private String encodeQuestionIds(String joinedQuestionIds) {
        byte[] encodedQuestionIds = Base64.getEncoder().encode(joinedQuestionIds.getBytes(StandardCharsets.UTF_8));
        return new String(encodedQuestionIds);
    }

    public ExamResult checkExamSolution(ExamSolution solution) {
        AchievedPoints achievedPoints = solution.calculatePoints();
        ExamStatus status = achievedPoints.value() < passThreshold.neededPoints()
                ? ExamStatus.FAILED
                : ExamStatus.PASSED;

        return new ExamResult(new Points(achievedPoints, availablePoints), status);
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

    public ExamPassThreshold getPassThreshold() {
        return passThreshold;
    }
}
