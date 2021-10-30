package dev.mazurkiewicz.quizer.auth;

import dev.mazurkiewicz.quizer.user.LoggedUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthHelper {

    public LoggedUserDetails getLoggedUser() {
        return (LoggedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
