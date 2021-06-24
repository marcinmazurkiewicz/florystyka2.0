package dev.mazurkiewicz.florystyka.recaptcha;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.recaptcha")
public class RecaptchaProperties {

    private String secretKey;
    private String hostname;
    private String verifyUrl;

}
