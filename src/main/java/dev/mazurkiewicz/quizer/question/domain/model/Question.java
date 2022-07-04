package dev.mazurkiewicz.quizer.question.domain.model;

import dev.mazurkiewicz.quizer.question.application.AnswerStatus;
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

    public AnswerResult checkAnswer(SelectedAnswer selectedAnswer) {
        AnswerStatus answerStatus = answers.stream()
                .filter(answer -> answer.type() == selectedAnswer.value())
                .findAny()
                .map(Answer::status)
                .orElse(AnswerStatus.INCORRECT);
        return new AnswerResult(answerStatus, correctAnswer().type());
    }

    public Answer correctAnswer() {
        return answers.stream()
                .filter(answer -> answer.status() == AnswerStatus.CORRECT)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Question %d without correct answer!", id.value())));
    }
}



