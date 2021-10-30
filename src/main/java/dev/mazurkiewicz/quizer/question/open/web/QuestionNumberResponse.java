package dev.mazurkiewicz.quizer.question.open.web;

import lombok.Value;

@Value
public class QuestionNumberResponse {

    long questionNumber;
    Integer earliestQuestionYear;
    Integer latestQuestionYear;

}
