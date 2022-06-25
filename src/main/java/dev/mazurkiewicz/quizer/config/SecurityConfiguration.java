package dev.mazurkiewicz.quizer.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    SecurityProperties properties;

    public List<String> allowedOrigins() {
        return properties.getAllowedOrigins();
    }

    public List<String> allowedMethods() {
        return properties.getAllowedMethods();
    }

    public List<String> allowedHeaders() {
        return properties.getAllowedHeaders();
    }

    public List<String> exposedHeaders() {
        return properties.getExposedHeaders();
    }
}
