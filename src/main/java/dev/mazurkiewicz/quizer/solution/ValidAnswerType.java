package dev.mazurkiewicz.quizer.solution;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnswerTypeValidator.class)
public @interface ValidAnswerType {

    String message() default "{dev.mazurkiewicz.quizer.request.validAnswerType.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
