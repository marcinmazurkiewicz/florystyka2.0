package dev.mazurkiewicz.quizer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "application.security")
public record SecurityProperties(List<String> allowedOrigins,
                                 List<String> allowedMethods,
                                 List<String> allowedHeaders,
                                 List<String> exposedHeaders) {
}
