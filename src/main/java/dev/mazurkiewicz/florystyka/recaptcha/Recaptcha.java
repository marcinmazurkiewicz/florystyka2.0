package dev.mazurkiewicz.florystyka.recaptcha;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RecaptchaValidator.class)
public @interface Recaptcha {
    String message() default "{dev.mazurkiewicz.florystyka.validation.Recaptcha.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
