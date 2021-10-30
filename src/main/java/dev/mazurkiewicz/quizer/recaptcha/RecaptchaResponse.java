package dev.mazurkiewicz.quizer.recaptcha;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecaptchaResponse {
    private final boolean success;
    private final String hostname;
    private final List<String> errorCodes;


    @JsonCreator
    public RecaptchaResponse(
            @JsonProperty("success") boolean success,
            @JsonProperty("hostname") String hostname,
            @JsonProperty("error-codes") List<String> errorCodes) {
        this.success = success;
        this.hostname = hostname;
        this.errorCodes = errorCodes;
    }
}
