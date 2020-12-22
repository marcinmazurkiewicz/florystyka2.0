package dev.mazurkiewicz.florystyka.question;

import lombok.Value;

@Value
public class QuestionNumberResponse {

    long questionNumber;
    Integer earliestQuestionYear;
    Integer latestQuestionYear;

}
