package dev.mazurkiewicz.quizer.user.validation;

import dev.mazurkiewicz.quizer.user.IUserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final IUserRepository userRepository;

    public UniqueUsernameValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userRepository.selectUserByUsername(username.toLowerCase()).isEmpty();
    }
}
