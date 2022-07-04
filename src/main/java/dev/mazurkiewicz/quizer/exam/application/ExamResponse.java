package dev.mazurkiewicz.quizer.exam.application;

import dev.mazurkiewicz.quizer.question.application.QuestionResponse;

import java.util.List;

public record ExamResponse(String examId, ExamTimer timer, List<QuestionResponse> questions) {
}
