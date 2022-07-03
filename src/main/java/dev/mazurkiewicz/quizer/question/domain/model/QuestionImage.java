package dev.mazurkiewicz.quizer.question.domain.model;

public record QuestionImage(String path) {

    boolean isImageAvailable() {
        return path != null && !path.isEmpty();
    }

}
