package dev.mazurkiewicz.quizer.question.domain;

import dev.mazurkiewicz.quizer.question.application.AnswerStatus;
import dev.mazurkiewicz.quizer.question.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {

    private Question testedQuestion;

    @BeforeEach
    void prepareAnswer() {
        QuestionId questionId = new QuestionId(1);
        QuestionContent questionContent = new QuestionContent("Test question #1");
        QuestionImage questionImage = new QuestionImage("/path/to/img.jpg");
        List<Answer> answers = List.of(
                new Answer(AnswerType.A, new AnswerContent("question #1 answer A"), AnswerStatus.INCORRECT),
                new Answer(AnswerType.B, new AnswerContent("question #1 answer B"), AnswerStatus.INCORRECT),
                new Answer(AnswerType.C, new AnswerContent("question #1 answer C"), AnswerStatus.CORRECT),
                new Answer(AnswerType.D, new AnswerContent("question #1 answer D"), AnswerStatus.INCORRECT));
        QuestionSourceExamDate questionSourceExamDate = new QuestionSourceExamDate(6, 2022);
        testedQuestion = new Question(questionId,
                questionContent,
                answers,
                questionImage,
                questionSourceExamDate);
    }

    @Test
    void shouldReturnTrueWhenSelectedAnswerIsCorrect() {
        SelectedAnswer selectedAnswer = new SelectedAnswer(AnswerType.C);
        AnswerResult expectedAnswerResult = new AnswerResult(AnswerStatus.CORRECT, AnswerType.C);

        AnswerResult answerResult = testedQuestion.checkAnswer(selectedAnswer);

        assertEquals(expectedAnswerResult, answerResult);
    }

    @Test
    void shouldReturnFalseWhenSelectedAnswerIsIncorrect() {
        SelectedAnswer selectedAnswer = new SelectedAnswer(AnswerType.B);
        AnswerResult expectedAnswerResult = new AnswerResult(AnswerStatus.INCORRECT, AnswerType.C);

        AnswerResult answerResult = testedQuestion.checkAnswer(selectedAnswer);

        assertEquals(expectedAnswerResult, answerResult);
    }

    @Test
    void shouldReturnFalseWhenSelectedAnswerIsEmpty() {
        SelectedAnswer selectedAnswer = new SelectedAnswer(AnswerType.EMPTY);
        AnswerResult expectedAnswerResult = new AnswerResult(AnswerStatus.INCORRECT, AnswerType.C);

        AnswerResult answerResult = testedQuestion.checkAnswer(selectedAnswer);

        assertEquals(expectedAnswerResult, answerResult);
    }

}