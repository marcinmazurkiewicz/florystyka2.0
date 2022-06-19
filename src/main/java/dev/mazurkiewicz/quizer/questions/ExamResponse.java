package dev.mazurkiewicz.quizer.questions;

import java.util.List;

public record ExamResponse(ExamTimer timer, List<QuestionResponse> questions) {
}
