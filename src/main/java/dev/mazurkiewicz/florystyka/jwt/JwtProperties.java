package dev.mazurkiewicz.florystyka.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterSeconds;
    private String issuer;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
