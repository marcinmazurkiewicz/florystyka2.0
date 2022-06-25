package dev.mazurkiewicz.quizer.config;

import java.util.List;

public interface SecurityProperties {

    List<String> getAllowedOrigins();

    List<String> getAllowedMethods();

    List<String> getAllowedHeaders();

    List<String> getExposedHeaders();
}
