package dev.mazurkiewicz.florystyka.user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = NewUserRolesValidator.class)
public @interface NewUserRoles {
    String message() default "{dev.mazurkiewicz.florystyka.user.validation.NewUserRoles.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        FieldMatch[] value();
    }
}
