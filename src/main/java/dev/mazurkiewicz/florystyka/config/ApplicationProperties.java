package dev.mazurkiewicz.florystyka.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "application.general")
public class ApplicationProperties {
    private Integer testQuestionsNumber;
    private String resourcesFolder;
    private String questionsImgFolder;
}
