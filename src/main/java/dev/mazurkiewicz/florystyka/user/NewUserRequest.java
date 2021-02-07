package dev.mazurkiewicz.florystyka.user;

import dev.mazurkiewicz.florystyka.auth.UserRole;
import dev.mazurkiewicz.florystyka.user.validation.FieldMatch;
import dev.mazurkiewicz.florystyka.user.validation.UniqueMail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword")
public class NewUserRequest {

    @UniqueMail
    private final String email;
    @NotEmpty
    private final String password;
    @NotEmpty
    private final String confirmPassword;
    List<UserRole> roles;


}
