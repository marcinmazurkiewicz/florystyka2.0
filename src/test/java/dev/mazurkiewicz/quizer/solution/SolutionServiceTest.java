package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.questions.AnswerType;
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
        CorrectAnswerEntity solution = new CorrectAnswerEntity();
        solution.setId(solutionRequest.questionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.questionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.questionId()).isEqualTo(solutionRequest.questionId());
        assertThat(response.selected()).isEqualTo(AnswerType.A);
        assertThat(response.correct()).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithInvalidAnswer() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(1, "f");
        CorrectAnswerEntity solution = new CorrectAnswerEntity();
        solution.setId(solutionRequest.questionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.questionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.questionId()).isEqualTo(solutionRequest.questionId());
        assertThat(response.selected()).isEqualTo(AnswerType.EMPTY);
        assertThat(response.correct()).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithEmptyAnswer() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(1, "");
        CorrectAnswerEntity solution = new CorrectAnswerEntity();
        solution.setId(solutionRequest.questionId());
        solution.setCorrect(AnswerType.B);
        when(repository.findById(solutionRequest.questionId())).thenReturn(Optional.of(solution));

        //when
        SolutionResponse response = service.checkSingleAnswer(solutionRequest);

        //then
        assertThat(response.questionId()).isEqualTo(solutionRequest.questionId());
        assertThat(response.selected()).isEqualTo(AnswerType.EMPTY);
        assertThat(response.correct()).isEqualTo(AnswerType.B);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenCallCheckSingleAnswerWithNonExistQuestionId() {
        //given
        SolutionRequest solutionRequest = new SolutionRequest(11, "A");
        when(repository.findById(solutionRequest.questionId())).thenReturn(Optional.empty());

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

        List<CorrectAnswerEntity> solutions = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            CorrectAnswerEntity solution = new CorrectAnswerEntity();
            solution.setId(i);
            solution.setCorrect(AnswerType.B);
            solutions.add(solution);
        }
        when(repository.findById(anyInt())).thenAnswer(invocation ->
                Optional.of(solutions.get(((Integer) invocation.getArguments()[0]) - 1)));

        //when
        TestSolutionResponse response = service.checkTest(request);

        //then
        assertThat(response.points()).isEqualTo(1);
        assertThat(response.total()).isEqualTo(5);
        assertThat(response.solutions()).hasSize(5);
        assertThat(response.solutions().get(1)).isEqualTo(solutionResponses.get(0));
        assertThat(response.solutions().get(2)).isEqualTo(solutionResponses.get(1));
        assertThat(response.solutions().get(3)).isEqualTo(solutionResponses.get(2));
        assertThat(response.solutions().get(4)).isEqualTo(solutionResponses.get(3));
        assertThat(response.solutions().get(5)).isEqualTo(solutionResponses.get(4));
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