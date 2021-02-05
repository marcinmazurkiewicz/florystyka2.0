package dev.mazurkiewicz.florystyka.auth;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class LoginRequest {

    @NotEmpty
    String username;
    @NotEmpty
    String password;

}
