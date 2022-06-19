package dev.mazurkiewicz.quizer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dev.mazurkiewicz.quizer")
public record QuizerProperties(Integer testQuestionsNumber,
                               String resourcesFolder,
                               String questionsImgFolder,
                               String pdfName,
                               Integer examTimeInSeconds) {

}

