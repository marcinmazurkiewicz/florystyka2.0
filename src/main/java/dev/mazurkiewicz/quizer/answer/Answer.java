package dev.mazurkiewicz.quizer.answer;

import lombok.Value;

@Value
public class Answer {

    AnswerType value;
    String content;

}