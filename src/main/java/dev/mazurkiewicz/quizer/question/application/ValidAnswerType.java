package dev.mazurkiewicz.quizer.question.application;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AnswerTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ValidAnswerType {

    String message() default "{dev.mazurkiewicz.quizer.request.validAnswerType.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
