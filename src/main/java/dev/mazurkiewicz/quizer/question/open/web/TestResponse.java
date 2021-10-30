package dev.mazurkiewicz.quizer.question.open.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mazurkiewicz.quizer.utils.TestTimer;
import lombok.Value;

import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestResponse {

    TestTimer timer;
    List<QuestionResponse> questions;
}
