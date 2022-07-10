package dev.mazurkiewicz.quizer;

import dev.mazurkiewicz.quizer.config.QuizerProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TestQuizerProperties implements QuizerProperties {
    private Integer examTimeInSeconds;
    private String pdfName;
    private String questionsImgFolder;
    private String resourcesFolder;
    private Integer testQuestionsNumber;
    private Integer examPassThreshold;
}
