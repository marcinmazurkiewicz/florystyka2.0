package dev.mazurkiewicz.florystyka.user.validation;

import dev.mazurkiewicz.florystyka.auth.UserRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NewUserRolesValidator implements ConstraintValidator<NewUserRoles, List<UserRole>> {
    @Override
    public boolean isValid(List<UserRole> userRoles, ConstraintValidatorContext constraintValidatorContext) {
        return isNotAnyRoles(userRoles) ||
                (isContainsTeacherOrStudentRole(userRoles) && isNotContainsAdminRoles(userRoles));
    }

    private boolean isNotAnyRoles(List<UserRole> userRoles) {
        return userRoles == null || userRoles.isEmpty();
    }

    private boolean isContainsTeacherOrStudentRole(List<UserRole> userRoles) {
        return userRoles.contains(UserRole.STUDENT) || userRoles.contains(UserRole.TEACHER);
    }

    private boolean isNotContainsAdminRoles(List<UserRole> userRoles) {
        return !userRoles.contains(UserRole.ADMIN) && !userRoles.contains(UserRole.MODERATOR);
    }
}
