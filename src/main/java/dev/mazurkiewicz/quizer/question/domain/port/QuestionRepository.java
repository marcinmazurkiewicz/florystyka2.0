package dev.mazurkiewicz.quizer.question.domain.port;

import dev.mazurkiewicz.quizer.question.domain.model.Question;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionInfo;

public interface QuestionRepository {

    Question getQuestionById(QuestionId id);

    Question getRandomQuestion();

    QuestionInfo getQuestionInfo();
}
