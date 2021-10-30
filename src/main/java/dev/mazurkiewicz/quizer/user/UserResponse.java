package dev.mazurkiewicz.quizer.user;

import lombok.Value;

import java.util.Set;

@Value
public class UserResponse {
    Long id;
    String email;
    Set<String> authorities;
}
