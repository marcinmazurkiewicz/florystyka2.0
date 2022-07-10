package dev.mazurkiewicz.quizer.exam.application.pdf;

import dev.mazurkiewicz.quizer.question.application.AnswerStatus;
import dev.mazurkiewicz.quizer.question.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PdfGeneratorTest {

    private final List<Question> questions = new ArrayList<>();

    @InjectMocks
    private PdfGenerator pdfGenerator;

    private Question prepareQuestion(int questionNumber) {
        List<Answer> answerList = new ArrayList<>();
        AnswerType correct;
        switch (questionNumber % 4) {
            case 0 -> correct = AnswerType.A;
            case 1 -> correct = AnswerType.B;
            case 2 -> correct = AnswerType.C;
            case 3 -> correct = AnswerType.D;
            default -> correct = AnswerType.EMPTY;
        }
        answerList.add(new Answer(AnswerType.A,
                new AnswerContent(String.format("Answer A for question %d", questionNumber)),
                AnswerStatus.of(correct == AnswerType.A)));
        answerList.add(new Answer(AnswerType.B,
                new AnswerContent(String.format("Answer B for question %d", questionNumber)),
                AnswerStatus.of(correct == AnswerType.B)));
        answerList.add(new Answer(AnswerType.C,
                new AnswerContent(String.format("Answer C for question %d", questionNumber)),
                AnswerStatus.of(correct == AnswerType.C)));
        answerList.add(new Answer(AnswerType.D,
                new AnswerContent(String.format("Answer D for question %d", questionNumber)),
                AnswerStatus.of(correct == AnswerType.D)));

        QuestionImage questionImage = null;
        if (questionNumber == 7 || questionNumber == 9)
            questionImage = new QuestionImage(String.format("quest_%d.png", questionNumber));

        return new Question(QuestionId.of(questionNumber),
                new QuestionContent(String.format("Question %d", questionNumber)),
                answerList,
                questionImage,
                new QuestionSourceExamDate(1, 2010));
    }

    @BeforeEach
    public void setup() {
        questions.clear();
        for (int i = 1; i < 11; i++) {
            questions.add(prepareQuestion(i));
        }
    }

    @Test
    void shouldReturnStringWithParsedHtmlWhenCallParseThymeleafTemplateWithEvenQuestionNumber() {
        //given
        //when
        String result = pdfGenerator.parseThymeleafTemplate(questions);

        //then
        assertThat(result).isEqualTo(PdfGeneratorExpectedTemplate.expectedHtmlWith10Questions);
    }

    @Test
    void shouldReturnStringWithParsedHtmlWhenCallParseThymeleafTemplateWithOddQuestionNumber() {
        //given
        questions.remove(9);
        //when
        String result = pdfGenerator.parseThymeleafTemplate(questions);

        //then
        assertThat(result).isEqualTo(PdfGeneratorExpectedTemplate.expectedHtmlWith9Questions);
    }

    @Test
    void shouldReturnStringWithParsedHtmlWhenCallParseThymeleafTemplateWithEmptyQuestionList() {
        //given
        questions.clear();
        //when
        String result = pdfGenerator.parseThymeleafTemplate(questions);

        //then
        assertThat(result).isEqualTo(PdfGeneratorExpectedTemplate.expectedHtmlWithNoQuestions);
    }
}