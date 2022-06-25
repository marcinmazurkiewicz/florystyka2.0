package dev.mazurkiewicz.quizer.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "application.security")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
class SecurityConfigurationProperties implements SecurityProperties {
    List<String> allowedOrigins;
    List<String> allowedMethods;
    List<String> allowedHeaders;
    List<String> exposedHeaders;
}