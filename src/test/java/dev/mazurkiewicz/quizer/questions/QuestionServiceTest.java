package dev.mazurkiewicz.quizer.questions;

import dev.mazurkiewicz.quizer.config.QuizerProperties;
import dev.mazurkiewicz.quizer.exception.PdfRenderException;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.pdf.PdfGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    private final QuizerProperties properties = new QuizerProperties(10, "", "");
    @Mock
    private QuestionRepository repository;
    @Mock
    private QuestionMapper mapper;
    @Mock
    private PdfGenerator pdfGenerator;
    private QuestionService service;

    @BeforeEach
    public void setup() {
        service = new QuestionService(repository, mapper, pdfGenerator, properties);
    }

    private QuestionEntity prepareQuestion(int questionNo) {
        QuestionEntity question = new QuestionEntity();
        question.setId(questionNo);
        question.setAnswerA(String.format("answer A for question %d", questionNo));
        question.setAnswerB(String.format("answer B for question %d", questionNo));
        question.setAnswerC(String.format("answer C for question %d", questionNo));
        question.setAnswerD(String.format("answer D for question %d", questionNo));
        question.setContent(String.format("Question %d content", questionNo));
        question.setCorrect(AnswerType.C);
        question.setImg(String.format("img_%d.png", questionNo));
        question.setMonth(1);
        question.setYear(2010);
        return question;
    }

    @Test
    void shouldReturnOneRandomQuestionResponse() {
        //given
        QuestionEntity question = prepareQuestion(1);
        QuestionResponse response = new QuestionResponse(
                question.getId(),
                question.getContent(),
                Arrays.asList(new Answer(AnswerType.A, question.getAnswerA()),
                        new Answer(AnswerType.B, question.getAnswerB()),
                        new Answer(AnswerType.C, question.getAnswerC()),
                        new Answer(AnswerType.D, question.getAnswerD())),
                question.getImg()
        );
        when(repository.getRandomQuestions(1)).thenReturn(Set.of(question));
        when(mapper.mapEntityToResponse(question)).thenReturn(response);

        //when
        QuestionResponse result = service.getRandomQuestion();

        //then
        verify(repository, times(1)).getRandomQuestions(1);
        verify(mapper, times(1)).mapEntityToResponse(question);
        assertThat(result).isEqualTo(response);
    }

    @Test
    void shouldThrowEmptyResultDataAccessExceptionWhenRepositoryReturnEmptySet() {
        //given
        when(repository.getRandomQuestions(1)).thenReturn(Set.of());

        //when
        //then
        assertThrows(EmptyResultDataAccessException.class, () -> service.getRandomQuestion());
        verify(repository, times(1)).getRandomQuestions(1);
        verify(mapper, never()).mapEntityToResponse(any());
    }

    @Test
    void shouldReturnQuestionResponseWhenQuestionWithIdExistInRepository() {
        QuestionEntity question = prepareQuestion(2);
        QuestionResponse response = new QuestionResponse(
                question.getId(),
                question.getContent(),
                Arrays.asList(new Answer(AnswerType.A, question.getAnswerA()),
                        new Answer(AnswerType.B, question.getAnswerB()),
                        new Answer(AnswerType.C, question.getAnswerC()),
                        new Answer(AnswerType.D, question.getAnswerD())),
                question.getImg()
        );
        when(repository.findById(2)).thenReturn(Optional.of(question));
        when(mapper.mapEntityToResponse(question)).thenReturn(response);

        //when
        QuestionResponse result = service.getQuestionById(2);

        //then
        verify(repository, times(1)).findById(2);
        verify(mapper, times(1)).mapEntityToResponse(question);
        assertThat(result).isEqualTo(response);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenQuestionWithIdNotExist() {
        //given
        when(repository.findById(3)).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getQuestionById(3));

        //then
        verify(repository, times(1)).findById(3);
        assertThat(exception.getMessage()).isEqualTo("Question with id 3 doesn't exist");
    }

    @Test
    void shouldReturnQuestionNumberResponseWhenCountQuestions() {
        //given
        long countResult = 100L;
        Integer earliestYearResult = 2010;
        Integer latestYearResult = 2020;
        when(repository.count()).thenReturn(countResult);
        when(repository.getEarliestYear()).thenReturn(earliestYearResult);
        when(repository.getLatestYear()).thenReturn(latestYearResult);
        QuestionNumberResponse expected = new QuestionNumberResponse(countResult, earliestYearResult, latestYearResult);

        //when
        QuestionNumberResponse result = service.countQuestions();

        //then
        verify(repository, times(1)).count();
        verify(repository, times(1)).getEarliestYear();
        verify(repository, times(1)).getLatestYear();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void shouldReturnEmptyQuestionNumberResponseWhenRepositoryReturnNulls() {
        //given
        long countResult = 0;
        Integer earliestYearResult = null;
        Integer latestYearResult = null;
        when(repository.count()).thenReturn(countResult);
        when(repository.getEarliestYear()).thenReturn(earliestYearResult);
        when(repository.getLatestYear()).thenReturn(latestYearResult);
        QuestionNumberResponse expected = new QuestionNumberResponse(countResult, earliestYearResult, latestYearResult);

        //when
        QuestionNumberResponse result = service.countQuestions();

        //then
        verify(repository, times(1)).count();
        verify(repository, times(1)).getEarliestYear();
        verify(repository, times(1)).getLatestYear();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void shouldReturnByteArrayWhenGetPdfTest() throws PdfRenderException {
        Set<QuestionEntity> questions = new HashSet<>();
        for (int i = 1; i < properties.testQuestionsNumber() + 1; i++) {
            questions.add(prepareQuestion(i));
        }
        byte[] expected = "mock result".getBytes();
        when(repository.getRandomQuestions(properties.testQuestionsNumber())).thenReturn(questions);
        when(pdfGenerator.generateTest(questions)).thenReturn(expected);

        //when
        byte[] result = service.getPdfTest();

        //then
        verify(repository, times(1)).getRandomQuestions(properties.testQuestionsNumber());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldReturnQuestionResponseListWhenGetQuestionsToTest() {
        //given
        Set<QuestionEntity> questions = new HashSet<>();
        for (int i = 1; i < properties.testQuestionsNumber() + 1; i++) {
            questions.add(prepareQuestion(i));
        }
        QuestionResponse response = new QuestionResponse(1, "mock content", new ArrayList<>(), "");
        when(repository.getRandomQuestions(properties.testQuestionsNumber())).thenReturn(questions);
        when(mapper.mapEntityToResponse(ArgumentMatchers.any(QuestionEntity.class))).thenReturn(response);

        //when
        List<QuestionResponse> result = service.getQuestionsToTest();

        //then
        verify(repository, times(1)).getRandomQuestions(properties.testQuestionsNumber());
        verify(mapper, times(properties.testQuestionsNumber())).mapEntityToResponse(ArgumentMatchers.any(QuestionEntity.class));
        assertThat(result).hasSize(properties.testQuestionsNumber());
    }

    @Test
    void shouldThrowIncorrectResultSizeDataAccessExceptionWhenRepositoryReturnNotEnoughQuestions() {
        //given
        Set<QuestionEntity> questions = new HashSet<>();
        for (int i = 1; i < properties.testQuestionsNumber(); i++) {
            questions.add(prepareQuestion(i));
        }
        QuestionResponse response = new QuestionResponse(1, "mock content", new ArrayList<>(), "");
        when(repository.getRandomQuestions(properties.testQuestionsNumber())).thenReturn(questions);

        //when
        //then
        assertThrows(IncorrectResultSizeDataAccessException.class, () -> service.getQuestionsToTest());
        verify(repository, times(1)).getRandomQuestions(properties.testQuestionsNumber());
        verify(mapper, never()).mapEntityToResponse(ArgumentMatchers.any(QuestionEntity.class));
    }

}