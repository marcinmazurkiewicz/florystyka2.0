package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.answer.Answer;
import lombok.Value;

import java.util.List;

@Value
public class QuestionResponse {

    Integer id;
    String content;
    List<Answer> answers;
    String img;

}
