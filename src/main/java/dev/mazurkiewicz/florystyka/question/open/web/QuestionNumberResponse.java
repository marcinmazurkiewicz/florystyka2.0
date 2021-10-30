package dev.mazurkiewicz.florystyka.question.open.web;

import lombok.Value;

@Value
public class QuestionNumberResponse {

    long questionNumber;
    Integer earliestQuestionYear;
    Integer latestQuestionYear;

}
