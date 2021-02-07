package dev.mazurkiewicz.florystyka.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String email;
    private boolean isAccountLocked;
    private boolean isEnabled;
    private Set<String> authorities;

}
