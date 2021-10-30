package dev.mazurkiewicz.quizer.question.open.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.quizer.answer.Answer;
import lombok.Value;

import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionResponse {

    Long id;
    String content;
    List<Answer> answers;
    String img;

}
