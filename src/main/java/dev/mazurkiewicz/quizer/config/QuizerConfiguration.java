package dev.mazurkiewicz.quizer.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QuizerConfiguration {

    QuizerProperties properties;

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

    public Integer examPassThreshold() {
        return properties.getExamPassThreshold();
    }
}
