package dev.mazurkiewicz.quizer;

import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestBeanConfig {

    @Bean
    @Primary
    public static QuizerConfiguration quizerProperties() {
        TestQuizerProperties properties = TestQuizerProperties.builder()
                .testQuestionsNumber(3)
                .examTimeInSeconds(600)
                .pdfName("exam.pdf")
                .questionsImgFolder("/img")
                .resourcesFolder("src/test/resources")
                .build();
        return new QuizerConfiguration(properties);
    }

    @Bean
    public MeterRegistry registry() {
        return new SimpleMeterRegistry();
    }

}
