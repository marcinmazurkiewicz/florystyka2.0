package io.dudek.florystyka.validation;

import io.dudek.florystyka.domain.User;
import io.dudek.florystyka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by md on 6/11/17.
 */
public class MailValidator implements ConstraintValidator<UniqueMail, String> {


    private UserRepository repository;

    @Autowired
    public MailValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueMail uniqueMail) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User user = repository.findByMail(s);
        return user == null;
    }
}
