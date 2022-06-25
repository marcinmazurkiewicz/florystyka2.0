package dev.mazurkiewicz.quizer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "dev.mazurkiewicz.quizer")
@Getter
@Setter
class QuizerConfigurationProperties implements QuizerProperties {
    private Integer examTimeInSeconds;
    private String pdfName;
    private String questionsImgFolder;
    private String resourcesFolder;
    private Integer testQuestionsNumber;
}
