package dev.mazurkiewicz.florystyka.question.open.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.florystyka.utils.TestTimer;
import lombok.Value;

import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestResponse {

    TestTimer timer;
    List<QuestionResponse> questions;
}
