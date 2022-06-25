package dev.mazurkiewicz.quizer.config;

public interface QuizerProperties {
    Integer getExamTimeInSeconds();

    String getPdfName();

    String getQuestionsImgFolder();

    String getResourcesFolder();

    Integer getTestQuestionsNumber();
}
