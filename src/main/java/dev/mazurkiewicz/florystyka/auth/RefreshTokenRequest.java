package dev.mazurkiewicz.florystyka.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class RefreshTokenRequest {

    @NotEmpty
    String refreshToken;

    @JsonCreator
    public RefreshTokenRequest(@JsonProperty("refreshToken") String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
