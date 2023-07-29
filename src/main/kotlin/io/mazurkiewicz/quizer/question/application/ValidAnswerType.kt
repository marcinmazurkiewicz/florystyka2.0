package io.mazurkiewicz.quizer.question.application

import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Constraint(validatedBy = [AnswerTypeValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FIELD
)
annotation class ValidAnswerType(
    val message: String = "{dev.mazurkiewicz.quizer.request.validAnswerType.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class AnswerTypeValidator : ConstraintValidator<ValidAnswerType, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        return try {
            AnswerType.of(value)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}

