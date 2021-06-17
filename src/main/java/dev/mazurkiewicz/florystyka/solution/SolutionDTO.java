package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class SolutionDTO {

    Integer questionId;
    AnswerType selectedAnswer;
    AnswerType correct;

}
