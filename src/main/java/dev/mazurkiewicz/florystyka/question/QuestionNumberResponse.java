package dev.mazurkiewicz.florystyka.question;

import lombok.Value;

@Value
public class QuestionNumberResponse {

    long questionNumber;
    int earliestQuestionYear;
    int latestQuestionYear;

}
