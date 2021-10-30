package dev.mazurkiewicz.quizer.user;

import dev.mazurkiewicz.quizer.auth.UserRole;
import dev.mazurkiewicz.quizer.recaptcha.Recaptcha;
import dev.mazurkiewicz.quizer.user.validation.FieldMatch;
import dev.mazurkiewicz.quizer.user.validation.NewUserRoles;
import dev.mazurkiewicz.quizer.user.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword")
public class NewUserRequest {

    @UniqueUsername
    private final String username;
    @NotEmpty
    private final String password;
    @NotEmpty
    private final String confirmPassword;
    @Recaptcha
    private final String captchaToken;
    @NewUserRoles
    List<UserRole> roles;


}
