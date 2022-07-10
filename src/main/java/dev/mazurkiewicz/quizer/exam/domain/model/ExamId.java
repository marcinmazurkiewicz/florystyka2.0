package dev.mazurkiewicz.quizer.exam.domain.model;

import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
public class ExamId {

    public final static String QUESTION_SPLITTER = "-";
    private final static String SPLITTER = "/";
    private final String value;

    public static ExamId of(String encodedQuestionIds) {
        return new ExamId(encodedQuestionIds);
    }

    public static ExamId encode(String joinedQuestionIds, Integer duration, Integer passThreshold) {
        String valueToEncoded = String.format("%d%s%d%s%s", duration, SPLITTER, passThreshold, SPLITTER, joinedQuestionIds);
        byte[] encodedQuestionIds = Base64.getEncoder().encode(valueToEncoded.getBytes(StandardCharsets.UTF_8));
        return new ExamId(new String(encodedQuestionIds));
    }

    public ExamParams decode() {
        byte[] decoded = Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
        String decodedId = new String(decoded);
        String[] idParts = decodedId.split("/");
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

    public String value() {
        return value;
    }
}
