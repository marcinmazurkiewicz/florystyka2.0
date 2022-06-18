package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.config.QuizerProperties;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.questions.AnswerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolutionServiceTest {

    @Mock
    private AnswerRepository repository;

    @Mock
    private QuizerProperties properties;

    @InjectMocks
    private SolutionService service;

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithValidAnswer() {
        //given
        AnswerRequest answerRequest = new AnswerRequest(1, "A");
        CorrectAnswerEntity solution = new CorrectAnswerEntity();
        solution.setQuestionId(answerRequest.questionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(answerRequest.questionId())).thenReturn(Optional.of(solution));

        //when
        AnswerResponse response = service.checkSingle(answerRequest);

        //then
        assertThat(response.points()).isEqualTo(0);
        assertThat(response.total()).isEqualTo(1);
        assertThat(response.correctAnswers()).isEqualTo(Map.of(answerRequest.questionId(), AnswerType.B));
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithEmptyAnswer() {
        //given
        AnswerRequest answerRequest = new AnswerRequest(1, "");
        CorrectAnswerEntity solution = new CorrectAnswerEntity();
        solution.setQuestionId(answerRequest.questionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(answerRequest.questionId())).thenReturn(Optional.of(solution));

        //when
        AnswerResponse response = service.checkSingle(answerRequest);

        //then
        assertThat(response.points()).isEqualTo(0);
        assertThat(response.total()).isEqualTo(1);
        assertThat(response.correctAnswers()).isEqualTo(Map.of(answerRequest.questionId(), AnswerType.B));
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallCheckSingleAnswerWithNonExistQuestionId() {
        //given
        AnswerRequest answerRequest = new AnswerRequest(11, "A");
        when(repository.findById(answerRequest.questionId())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.checkSingle(answerRequest));

        //then
        assertThat(exception.getMessage()).isEqualTo("Question with id 11 doesn't exist");
    }

    @Test
    void shouldReturnTestSolutionResponseWhenCallCheckTestWithValidAnswers() {
        //given
        List<AnswerRequest> request = Arrays.asList(
                new AnswerRequest(1, "A"),
                new AnswerRequest(2, "B"),
                new AnswerRequest(3, "C"),
                new AnswerRequest(4, "D"),
                new AnswerRequest(5, "")
        );

        Map<Integer, AnswerType> expectedAnswers = Map.of(
                1, AnswerType.B,
                2, AnswerType.B,
                3, AnswerType.B,
                4, AnswerType.B,
                5, AnswerType.B
        );

        List<CorrectAnswerEntity> solutions = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            CorrectAnswerEntity solution = new CorrectAnswerEntity();
            solution.setQuestionId(i);
            solution.setCorrect(AnswerType.B);
            solutions.add(solution);
        }
        when(repository.findAllById(ArgumentMatchers.anyCollection())).thenReturn(solutions);
        when(properties.testQuestionsNumber()).thenReturn(5);

        //when
        AnswerResponse response = service.checkTest(request);

        //then
        assertThat(response.points()).isEqualTo(1);
        assertThat(response.total()).isEqualTo(5);
        assertThat(response.correctAnswers()).hasSize(5);
        assertThat(response.correctAnswers()).isEqualTo(expectedAnswers);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallCheckTestWithNonExistQuestionId() {
        //given
        List<AnswerRequest> request = Arrays.asList(
                new AnswerRequest(11, "A"),
                new AnswerRequest(2, "B"),
                new AnswerRequest(3, "C"),
                new AnswerRequest(4, "D"),
                new AnswerRequest(5, "")
        );

        List<CorrectAnswerEntity> correctAnswerEntities = List.of(
                new CorrectAnswerEntity(2, AnswerType.B),
                new CorrectAnswerEntity(3, AnswerType.B),
                new CorrectAnswerEntity(4, AnswerType.B),
                new CorrectAnswerEntity(5, AnswerType.B)
        );

        when(repository.findAllById(ArgumentMatchers.anyCollection())).thenReturn(correctAnswerEntities);
        when(properties.testQuestionsNumber()).thenReturn(5);

        //when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.checkTest(request));

        //then
        verify(repository, times(1)).findAllById(ArgumentMatchers.anyCollection());
        assertThat(exception.getMessage()).isEqualTo("Question(s) with id: 11 not found");
    }
}