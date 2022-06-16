package dev.mazurkiewicz.quizer.questions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionMapperTest {

    private final QuestionMapper mapper = new QuestionMapper();
    private final int questionId = 1;
    private final String questionContent = "Content of question";
    private final String questionAnswerA = "Answer A";
    private final String questionAnswerB = "Answer B";
    private final String questionAnswerC = "Answer C";
    private final String questionAnswerD = "Answer D";
    private final AnswerType questionCorrect = AnswerType.C;
    private final String questionImg = "img_1.png";
    private final Map<String, String> expectedContent = new HashMap<>();
    private QuestionEntity question;

    @BeforeEach
    public void setup() {
        question = prepareQuestion();
        resetExpectedContent();
    }

    private QuestionEntity prepareQuestion() {
        QuestionEntity question = new QuestionEntity();
        question.setId(questionId);
        question.setContent(questionContent);
        question.setAnswerA(questionAnswerA);
        question.setAnswerB(questionAnswerB);
        question.setAnswerC(questionAnswerC);
        question.setAnswerD(questionAnswerD);
        question.setCorrect(questionCorrect);
        question.setImg(questionImg);
        int questionYear = 2020;
        question.setYear(questionYear);
        int questionMonth = 10;
        question.setMonth(questionMonth);
        return question;
    }

    private void resetExpectedContent() {
        expectedContent.clear();
        expectedContent.put("questionContent", questionContent);
        expectedContent.put("answerA", questionAnswerA);
        expectedContent.put("answerB", questionAnswerB);
        expectedContent.put("answerC", questionAnswerC);
        expectedContent.put("answerD", questionAnswerD);
        expectedContent.put("imagePath", String.format("/resources/img/%s", questionImg));
    }

    private void assertResponseFields(QuestionResponse response, Integer expectedId, Map<String, String> expectedContent) {
        assertThat(response.id()).isEqualTo(expectedId);
        assertThat(response.content()).isEqualTo(expectedContent.get("questionContent"));
        assertThat(response.answers()).hasSize(4);
        assertThat(response.answers()).containsOnlyOnce(new Answer(AnswerType.A, expectedContent.get("answerA")));
        assertThat(response.answers()).containsOnlyOnce(new Answer(AnswerType.B, expectedContent.get("answerB")));
        assertThat(response.answers()).containsOnlyOnce(new Answer(AnswerType.C, expectedContent.get("answerC")));
        assertThat(response.answers()).containsOnlyOnce(new Answer(AnswerType.D, expectedContent.get("answerD")));
        assertThat(response.img()).isEqualTo(expectedContent.get("imagePath"));
    }

    @Test
    void shouldReturnQuestionResponse() {
        //given
        //when
        QuestionResponse response = mapper.mapEntityToResponse(question);

        //then
        assertResponseFields(response, questionId, expectedContent);
    }

    @Test
    void shouldReturnQuestionResponseWithNullImgWhenQuestionImgIsNull() {
        //given
        question.setImg(null);
        expectedContent.put("imagePath", null);

        //when
        QuestionResponse response = mapper.mapEntityToResponse(question);

        //then
        assertResponseFields(response, questionId, expectedContent);
    }


    @Test
    void shouldReturnQuestionResponseWithNullImgWhenQuestionImgIsEmptyString() {
        //given
        question.setImg("");
        expectedContent.put("imagePath", null);

        //when
        QuestionResponse response = mapper.mapEntityToResponse(question);

        //then
        assertResponseFields(response, questionId, expectedContent);
    }

    @Test
    void shouldReturnQuestionResponseWithNullImgWhenQuestionWithNotSetFields() {
        //given
        QuestionEntity quest = new QuestionEntity();
        expectedContent.clear();

        //when
        QuestionResponse response = mapper.mapEntityToResponse(quest);

        //then
        assertResponseFields(response, null, expectedContent);
    }

    @Test
    void shouldThrowIllegalStateExceptionWithNullImgWhenQuestionIsNull() {
        //given
        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> mapper.mapEntityToResponse(null));

        //then
        assertThat(exception.getMessage()).isEqualTo("Question cannot be empty");
    }

}
