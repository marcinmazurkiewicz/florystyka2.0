package dev.mazurkiewicz.quizer.question.application;

import dev.mazurkiewicz.quizer.question.domain.model.AnswerType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerTypeValidator implements ConstraintValidator<ValidAnswerType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        AnswerType answerType = null;
        try {
            answerType = AnswerType.of(value);
        } catch (IllegalArgumentException e) {
            log.warn("{}: {}", e.getMessage(), value);
        }
        return answerType != null;
    }
}
