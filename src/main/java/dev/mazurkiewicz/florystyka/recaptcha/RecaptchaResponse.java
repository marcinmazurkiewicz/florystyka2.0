package dev.mazurkiewicz.florystyka.recaptcha;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecaptchaResponse {
    private final boolean success;
    private final String hostname;
    private final float score;
    private final String action;


    @JsonCreator
    public RecaptchaResponse(
            @JsonProperty("success") boolean success,
            @JsonProperty("hostname") String hostname,
            @JsonProperty("score") float score,
            @JsonProperty("action") String action
    ) {
        this.success = success;
        this.hostname = hostname;
        this.score = score;
        this.action = action;
    }
}
