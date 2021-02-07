package dev.mazurkiewicz.florystyka.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotNullFileValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullFile {
    String message() default "{dev.mazurkiewicz.florystyka.validation.NotNullFile.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
