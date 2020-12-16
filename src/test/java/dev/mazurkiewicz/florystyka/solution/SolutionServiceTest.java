package dev.mazurkiewicz.florystyka.solution;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
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
        SolutionRequest solutionRequest = new SolutionRequest(1, "A");
        Solution solution = new Solution();
        solution.setId(solutionRequest.getQuestionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.getQuestionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.getQuestionId()).isEqualTo(solutionRequest.getQuestionId());
        assertThat(response.getSelected()).isEqualTo(AnswerType.A);
        assertThat(response.getCorrect()).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithInvalidAnswer() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(1, "f");
        Solution solution = new Solution();
        solution.setId(solutionRequest.getQuestionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.getQuestionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.getQuestionId()).isEqualTo(solutionRequest.getQuestionId());
        assertThat(response.getSelected()).isEqualTo(AnswerType.EMPTY);
        assertThat(response.getCorrect()).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithEmptyAnswer() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(1, "");
        Solution solution = new Solution();
        solution.setId(solutionRequest.getQuestionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.getQuestionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.getQuestionId()).isEqualTo(solutionRequest.getQuestionId());
        assertThat(response.getSelected()).isEqualTo(AnswerType.EMPTY);
        assertThat(response.getCorrect()).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallCheckSingleAnswerWithNonExistQuestionId() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(11, "A");
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
                new SolutionRequest(1, "A"),
                new SolutionRequest(2, "B"),
                new SolutionRequest(3, "C"),
                new SolutionRequest(4, "D"),
                new SolutionRequest(5, "")
        );

        List<SolutionResponse> solutionResponses = Arrays.asList(
                new SolutionResponse(1, AnswerType.B, AnswerType.A),
                new SolutionResponse(2, AnswerType.B, AnswerType.B),
                new SolutionResponse(3, AnswerType.B, AnswerType.C),
                new SolutionResponse(4, AnswerType.B, AnswerType.D),
                new SolutionResponse(5, AnswerType.B, AnswerType.EMPTY)
        );

        List<Solution> solutions = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Solution solution = new Solution();
            solution.setId(i);
            solution.setCorrect(AnswerType.B);
            solutions.add(solution);
        }
        when(repository.findById(anyInt())).thenAnswer(invocation ->
                Optional.of(solutions.get(((Integer) invocation.getArguments()[0]) - 1)));

        //when
        TestSolutionResponse response = service.checkTest(request);

        //then
        assertThat(response.getPoints()).isEqualTo(1);
        assertThat(response.getTotal()).isEqualTo(5);
        assertThat(response.getSolutions()).hasSize(5);
        assertThat(response.getSolutions().get(1)).isEqualTo(solutionResponses.get(0));
        assertThat(response.getSolutions().get(2)).isEqualTo(solutionResponses.get(1));
        assertThat(response.getSolutions().get(3)).isEqualTo(solutionResponses.get(2));
        assertThat(response.getSolutions().get(4)).isEqualTo(solutionResponses.get(3));
        assertThat(response.getSolutions().get(5)).isEqualTo(solutionResponses.get(4));
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallCheckTestWithNonExistQuestionId() {
        //given
        List<SolutionRequest> request = Arrays.asList(
                new SolutionRequest(11, "A"),
                new SolutionRequest(2, "B"),
                new SolutionRequest(3, "C"),
                new SolutionRequest(4, "D"),
                new SolutionRequest(5, "")
        );
        when(repository.findById(11)).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.checkTest(request));

        //then
        assertThat(exception.getMessage()).isEqualTo("Question with id 11 doesn't exist");
    }
}