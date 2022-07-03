package dev.mazurkiewicz.quizer.question.domain.port;

import dev.mazurkiewicz.quizer.question.domain.model.Question;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionInfo;
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
}
