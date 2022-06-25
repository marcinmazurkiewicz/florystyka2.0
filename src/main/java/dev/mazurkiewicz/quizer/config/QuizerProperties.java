package dev.mazurkiewicz.quizer.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@ConfigurationProperties(prefix = "dev.mazurkiewicz.quizer")
@Getter
@NoArgsConstructor
@Setter
public class QuizerProperties {
    private Integer examTimeInSeconds;
    private String pdfName;
    private String questionsImgFolder;
    private String resourcesFolder;
    private Integer testQuestionsNumber;
}
