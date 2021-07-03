package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SolutionServiceTest {

    @Mock
    private SolutionRepository repository;

    @InjectMocks
    private SolutionService service;

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithValidAnswer() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(1, AnswerType.A);
        Solution solution = new Solution();
        solution.setId(solutionRequest.getQuestionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.getQuestionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.getPoints()).isEqualTo(0);
        assertThat(response.getTotal()).isEqualTo(1);
        assertThat(response.getSolutions().get(solutionRequest.getQuestionId())).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithInvalidAnswer() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(1, AnswerType.EMPTY);
        Solution solution = new Solution();
        solution.setId(solutionRequest.getQuestionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.getQuestionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.getPoints()).isEqualTo(0);
        assertThat(response.getTotal()).isEqualTo(1);
        assertThat(response.getSolutions().get(solutionRequest.getQuestionId())).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallCheckSingleAnswerWithNonExistQuestionId() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(11, AnswerType.A);
        when(repository.findById(solutionRequest.getQuestionId())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.checkSingleAnswer(solutionRequest));

        //then
        assertThat(exception.getMessage()).isEqualTo("Question with id 11 doesn't exist");
    }

    @Test
    void shouldReturnTestSolutionResponseWhenCallCheckTestWithValidAnswers() {
        //given
        List<SolutionRequest> request = Arrays.asList(
                new SolutionRequest(1, AnswerType.A),
                new SolutionRequest(2, AnswerType.B),
                new SolutionRequest(3, AnswerType.C),
                new SolutionRequest(4, AnswerType.D),
                new SolutionRequest(5, AnswerType.EMPTY)
        );

        List<Solution> solutions = new ArrayList<>();
        for (long i = 1; i < 6; i++) {
            Solution solution = new Solution();
            solution.setId(i);
            solution.setCorrect(AnswerType.B);
            solutions.add(solution);
        }
        when(repository.findAllById(anyIterable())).thenReturn(solutions);

        //when
        SolutionResponse response = service.checkTest(request);

        //then
        assertThat(response.getPoints()).isEqualTo(1);
        assertThat(response.getTotal()).isEqualTo(5);
        assertThat(response.getSolutions()).hasSize(5);
        assertThat(response.getSolutions().get(1)).isEqualTo(AnswerType.B);
        assertThat(response.getSolutions().get(2)).isEqualTo(AnswerType.B);
        assertThat(response.getSolutions().get(3)).isEqualTo(AnswerType.B);
        assertThat(response.getSolutions().get(4)).isEqualTo(AnswerType.B);
        assertThat(response.getSolutions().get(5)).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallCheckTestWithNonExistQuestionId() {
        //given
        List<SolutionRequest> request = Arrays.asList(
                new SolutionRequest(1, AnswerType.A),
                new SolutionRequest(2, AnswerType.B),
                new SolutionRequest(3, AnswerType.C),
                new SolutionRequest(4, AnswerType.D),
                new SolutionRequest(5, AnswerType.EMPTY)
        );

        List<Solution> solutions = new ArrayList<>();
        for (long i = 1; i < 5; i++) {
            Solution solution = new Solution();
            solution.setId(i);
            solution.setCorrect(AnswerType.B);
            solutions.add(solution);
        }

        when(repository.findAllById(anyIterable())).thenReturn(solutions);

        //when
        Exception exception = assertThrows(IncorrectResultSizeDataAccessException.class, () -> service.checkTest(request));

        //then
        assertThat(exception.getMessage()).isEqualTo("Some answers are missing");
    }
}