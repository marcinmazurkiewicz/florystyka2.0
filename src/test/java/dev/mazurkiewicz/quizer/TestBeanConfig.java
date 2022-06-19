package dev.mazurkiewicz.quizer;

import dev.mazurkiewicz.quizer.config.QuizerProperties;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestBeanConfig {

    @Bean
    @Primary
    public static QuizerProperties quizerProperties() {
        return new QuizerProperties(3,
                "src/test/resources",
                "/img",
                "exam.pdf",
                600);
    }

    @Bean
    public MeterRegistry registry() {
        return new SimpleMeterRegistry();
    }

}
