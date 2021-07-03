package dev.mazurkiewicz.florystyka.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.florystyka.answer.Answer;
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
