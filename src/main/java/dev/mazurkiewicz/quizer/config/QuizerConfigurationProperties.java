package dev.mazurkiewicz.quizer.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "dev.mazurkiewicz.quizer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
class QuizerConfigurationProperties implements QuizerProperties {
    Integer examTimeInSeconds;
    String pdfName;
    String questionsImgFolder;
    String resourcesFolder;
    Integer testQuestionsNumber;
    Integer examPassThreshold;
}
