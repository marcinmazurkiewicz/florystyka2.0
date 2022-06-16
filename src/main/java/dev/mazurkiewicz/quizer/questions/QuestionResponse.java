package dev.mazurkiewicz.quizer.questions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record QuestionResponse(Integer id, String content, List<Answer> answers, String img) {
}
