package dev.mazurkiewicz.quizer.question.infrastructure.db;

import dev.mazurkiewicz.quizer.question.application.AnswerStatus;
import dev.mazurkiewicz.quizer.question.domain.model.Answer;
import dev.mazurkiewicz.quizer.question.domain.model.AnswerContent;
import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;

import java.util.List;

public class ExtractionAnswerUtil {

    public static String extractAnswerFromList(List<Answer> answers, AnswerType searchedAnswerType) {
        return answers.stream()
                .filter(answer -> answer.type() == searchedAnswerType)
                .findFirst()
                .map(Answer::content)
                .map(AnswerContent::value)
                .orElse("");
    }

    public static AnswerType extractCorrectAnswerTypeFromList(List<Answer> answers) {
        return answers.stream()
                .filter(answer -> answer.status() == AnswerStatus.CORRECT)
                .findFirst()
                .map(Answer::type)
                .orElse(AnswerType.EMPTY);
    }
}
