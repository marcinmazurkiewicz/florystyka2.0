package dev.mazurkiewicz.florystyka.recaptcha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RecaptchaValidator implements ConstraintValidator<Recaptcha, String> {

    private final RecaptchaService recaptchaService;

    public RecaptchaValidator(RecaptchaService recaptchaService) {
        this.recaptchaService = recaptchaService;
    }

    @Override
    public boolean isValid(String token, ConstraintValidatorContext constraintValidatorContext) {
        return recaptchaService.verifyCaptcha(token);
    }
}
