package dev.mazurkiewicz.quizer.pdf;

import dev.mazurkiewicz.quizer.answer.AnswerType;
import dev.mazurkiewicz.quizer.question.open.db.Question;
import dev.mazurkiewicz.quizer.resource.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PdfGeneratorTest {

    private final List<Question> questions = new ArrayList<>();

    @Mock
    private ResourceService resourceService;

    @InjectMocks
    private PdfGenerator pdfGenerator;

    private Question prepareQuestion(int questionNumber) {
        Question question = new Question();
        question.setAnswerA(String.format("Answer A for question %d", questionNumber));
        question.setAnswerB(String.format("Answer B for question %d", questionNumber));
        question.setAnswerC(String.format("Answer C for question %d", questionNumber));
        question.setAnswerD(String.format("Answer D for question %d", questionNumber));
        question.setContent(String.format("Question %d", questionNumber));
        AnswerType correct;
        switch (questionNumber % 4) {
            case 0 -> correct = AnswerType.A;
            case 1 -> correct = AnswerType.B;
            case 2 -> correct = AnswerType.C;
            case 3 -> correct = AnswerType.D;
            default -> correct = AnswerType.EMPTY;
        }
        question.setCorrect(correct);
        if (questionNumber == 7 || questionNumber == 9)
            question.setImg(String.format("quest_%d.png", questionNumber));
        question.setMonth(1);
        question.setYear(2010);
        return question;
    }

    @BeforeEach
    public void setup() {
        questions.clear();
        for (int i = 1; i < 11; i++) {
            questions.add(prepareQuestion(i));
        }
    }

//    @Test
//    void generatePdfFromHtml() {
//
//    }
//
//    @Test
//    void generateTest() {
//    }
//
//    @Test
//    void shouldReturnStringWithParsedHtmlWhenCallParseThymeleafTemplateWithEvenQuestionNumber() {
//        //given
//        //when
//        String result = pdfGenerator.parseThymeleafTemplate(questions);
//
//        //then
//        assertThat(result).isEqualTo(PdfGeneratorExpectedTemplate.expectedHtmlWith10Questions);
//    }
//
//    @Test
//    void shouldReturnStringWithParsedHtmlWhenCallParseThymeleafTemplateWithOddQuestionNumber() {
//        //given
//        questions.remove(9);
//        //when
//        String result = pdfGenerator.parseThymeleafTemplate(questions);
//
//        //then
//        assertThat(result).isEqualTo(PdfGeneratorExpectedTemplate.expectedHtmlWith9Questions);
//    }
//
//    @Test
//    void shouldReturnStringWithParsedHtmlWhenCallParseThymeleafTemplateWithEmptyQuestionList() {
//        //given
//        questions.clear();
//        //when
//        String result = pdfGenerator.parseThymeleafTemplate(questions);
//
//        //then
//        assertThat(result).isEqualTo(PdfGeneratorExpectedTemplate.expectedHtmlWithNoQuestions);
//    }
}