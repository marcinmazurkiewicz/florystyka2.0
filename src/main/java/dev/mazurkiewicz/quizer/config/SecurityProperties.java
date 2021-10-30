package dev.mazurkiewicz.quizer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.ZoneId;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.security")
public class SecurityProperties {

    private List<String> allowedOrigins;
    private String refreshTokenHeader;
    private Long refreshTokenExpirationAfterSeconds;
    private String prometheusIPAddress;
    private Boolean userAccess;

    public ZoneId getTimezoneId() {
        return ZoneId.of("Europe/Warsaw");
    }
}
