package dev.mazurkiewicz.quizer.question.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Question {

    private QuestionId id;
    private QuestionContent content;
    private List<Answer> answers;
    private QuestionImage image;
    private QuestionSourceExamDate sourceExamDate;

    public boolean checkAnswer(AnswerType chosenAnswer) {
        return answers.stream()
                .filter(answer -> answer.type() == chosenAnswer)
                .findAny()
                .map(Answer::isCorrect)
                .orElse(false);
    }
}



