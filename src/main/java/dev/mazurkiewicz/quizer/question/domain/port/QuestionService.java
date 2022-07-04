package dev.mazurkiewicz.quizer.question.domain.port;

import dev.mazurkiewicz.quizer.question.domain.model.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    public Question getQuestion(QuestionId questionId) {
        return repository.getQuestionById(questionId);
    }

    public Question getRandomQuestion() {
        return repository.getRandomQuestion();
    }

    public QuestionInfo getQuestionInfo() {
        return repository.getQuestionInfo();

    }

    public AnswerResult checkAnswer(QuestionId questionId, SelectedAnswer selectedAnswer) {
        Question question = repository.getQuestionById(questionId);
        return question.checkAnswer(selectedAnswer);
    }
}
