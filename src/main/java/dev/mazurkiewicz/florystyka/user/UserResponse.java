package dev.mazurkiewicz.florystyka.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Set;

@Value
public class UserResponse {
    Long id;
    String email;
    Set<String> authorities;
}
