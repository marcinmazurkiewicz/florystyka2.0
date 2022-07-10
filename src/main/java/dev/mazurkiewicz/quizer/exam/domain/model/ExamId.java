package dev.mazurkiewicz.quizer.exam.domain.model;

import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public record ExamId(String value) {

    public final static String QUESTION_SPLITTER = "-";
    private final static String SPLITTER = "/";

    public static ExamId of(String encodedQuestionIds) {
        return new ExamId(encodedQuestionIds);
    }

    public static ExamId encode(String joinedQuestionIds, Integer duration, Integer passThreshold) {
        String valueToEncoded = String.format("%d%s%d%s%s", duration, SPLITTER, passThreshold, SPLITTER, joinedQuestionIds);
        byte[] encodedValue = Base64.getEncoder().encode(valueToEncoded.getBytes(StandardCharsets.UTF_8));
        return new ExamId(new String(encodedValue));
    }

    public ExamParams decode() {
        byte[] decodedValue = Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
        String decodedString = new String(decodedValue);
        String[] idParts = decodedString.split("/");
        int durationPart = Integer.parseInt(idParts[0]);
        ExamDuration examDuration = ExamDuration.fromSeconds(durationPart);
        Integer passThresholdPart = Integer.valueOf(idParts[1]);
        ExamPassThreshold passThreshold = ExamPassThreshold.of(passThresholdPart);
        List<QuestionId> questionIds = Arrays.stream(idParts[2].split(QUESTION_SPLITTER))
                .map(Integer::valueOf)
                .map(QuestionId::of)
                .toList();
        return new ExamParams(examDuration, passThreshold, questionIds);
    }
}
