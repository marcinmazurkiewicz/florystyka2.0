package dev.mazurkiewicz.florystyka.user.validation;

import dev.mazurkiewicz.florystyka.user.IUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMailValidator implements ConstraintValidator<UniqueMail, String> {

    private final IUserRepository userRepository;

    public UniqueMailValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueMail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {
        return userRepository.findByEmail(mail.toLowerCase()).isEmpty();
    }
}
