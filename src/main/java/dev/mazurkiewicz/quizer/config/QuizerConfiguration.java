package dev.mazurkiewicz.quizer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizerConfiguration {
    private final QuizerProperties properties;

    public Integer examTimeInSeconds() {
        return properties.getExamTimeInSeconds();

    }

    public String pdfName() {
        return properties.getPdfName();
    }

    public String questionsImgFolder() {
        return properties.getQuestionsImgFolder();
    }

    public String resourcesFolder() {
        return properties.getResourcesFolder();
    }

    public Integer examQuestionsNumber() {
        return properties.getTestQuestionsNumber();
    }
}
