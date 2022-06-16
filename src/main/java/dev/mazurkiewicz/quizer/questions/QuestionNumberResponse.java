package dev.mazurkiewicz.quizer.questions;

public record QuestionNumberResponse(long questionNumber, Integer earliestQuestionYear, Integer latestQuestionYear) {
}
