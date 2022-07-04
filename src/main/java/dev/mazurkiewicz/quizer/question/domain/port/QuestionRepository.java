package dev.mazurkiewicz.quizer.question.domain.port;

import dev.mazurkiewicz.quizer.exam.domain.port.QuestionNumber;
import dev.mazurkiewicz.quizer.question.domain.model.Question;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionInfo;

import java.util.List;

public interface QuestionRepository {

    Question getQuestionById(QuestionId id);

    List<Question> getRandomQuestions(QuestionNumber questionNumber);

    Question getRandomQuestion();

    QuestionInfo getQuestionInfo();
}
