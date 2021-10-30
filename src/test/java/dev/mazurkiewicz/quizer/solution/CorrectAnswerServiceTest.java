package dev.mazurkiewicz.quizer.solution;

import dev.mazurkiewicz.quizer.solution.open.domain.AnswerCheckingService;
import dev.mazurkiewicz.quizer.solution.open.db.CorrectAnswerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CorrectAnswerServiceTest {

    @Mock
    private CorrectAnswerRepository repository;

    @InjectMocks
    private AnswerCheckingService service;

//    @Test
//    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithValidAnswer() {
//        //given
//        CorrectAnwserCheckRequest correctAnwserCheckRequest = new CorrectAnwserCheckRequest(1, AnswerType.A);
//        CorrectAnswer correctAnswer = new CorrectAnswer();
//        correctAnswer.setQuestionId(correctAnwserCheckRequest.getQuestionId());
//        correctAnswer.setCorrect(AnswerType.B);
//        when(repository.findById(correctAnwserCheckRequest.getQuestionId())).thenReturn(Optional.of(correctAnswer));
//
//        //when
//        SolutionResponse response = service.checkSingleAnswer(correctAnwserCheckRequest);
//
//        //then
//        assertThat(response.getPoints()).isEqualTo(0);
//        assertThat(response.getTotal()).isEqualTo(1);
//        assertThat(response.getSolutions().get(correctAnwserCheckRequest.getQuestionId())).isEqualTo(AnswerType.B);
//    }
//
//    @Test
//    void shouldReturnSolutionResponseWhenCallCheckSingleAnswerWithInvalidAnswer() {
//        //given
//        CorrectAnwserCheckRequest correctAnwserCheckRequest = new CorrectAnwserCheckRequest(1, AnswerType.EMPTY);
//        CorrectAnswer correctAnswer = new CorrectAnswer();
//        correctAnswer.setQuestionId(correctAnwserCheckRequest.getQuestionId());
//        correctAnswer.setCorrect(AnswerType.B);
//        when(repository.findById(correctAnwserCheckRequest.getQuestionId())).thenReturn(Optional.of(correctAnswer));
//
//        //when
//        SolutionResponse response = service.checkSingleAnswer(correctAnwserCheckRequest);
//
//        //then
//        assertThat(response.getPoints()).isEqualTo(0);
//        assertThat(response.getTotal()).isEqualTo(1);
//        assertThat(response.getSolutions().get(correctAnwserCheckRequest.getQuestionId())).isEqualTo(AnswerType.B);
//    }
//
//    @Test
//    void shouldThrowResourceNotFoundExceptionWhenCallCheckSingleAnswerWithNonExistQuestionId() {
//        //given
//        CorrectAnwserCheckRequest correctAnwserCheckRequest = new CorrectAnwserCheckRequest(11, AnswerType.A);
//        when(repository.findById(correctAnwserCheckRequest.getQuestionId())).thenReturn(Optional.empty());
//
//        //when
//        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.checkSingleAnswer(correctAnwserCheckRequest));
//
//        //then
//        assertThat(exception.getMessage()).isEqualTo("Question with id 11 doesn't exist");
//    }
//
//    @Test
//    void shouldReturnTestSolutionResponseWhenCallCheckTestWithValidAnswers() {
//        //given
//        List<CorrectAnwserCheckRequest> request = Arrays.asList(
//                new CorrectAnwserCheckRequest(1, AnswerType.A),
//                new CorrectAnwserCheckRequest(2, AnswerType.B),
//                new CorrectAnwserCheckRequest(3, AnswerType.C),
//                new CorrectAnwserCheckRequest(4, AnswerType.D),
//                new CorrectAnwserCheckRequest(5, AnswerType.EMPTY)
//        );
//
//        List<CorrectAnswer> correctAnswers = new ArrayList<>();
//        for (long i = 1; i < 6; i++) {
//            CorrectAnswer correctAnswer = new CorrectAnswer();
//            correctAnswer.setQuestionId(i);
//            correctAnswer.setCorrect(AnswerType.B);
//            correctAnswers.add(correctAnswer);
//        }
//        when(repository.findAllById(anyIterable())).thenReturn(correctAnswers);
//
//        //when
//        SolutionResponse response = service.checkTest(request);
//
//        //then
//        assertThat(response.getPoints()).isEqualTo(1);
//        assertThat(response.getTotal()).isEqualTo(5);
//        assertThat(response.getSolutions()).hasSize(5);
//        assertThat(response.getSolutions().get(1)).isEqualTo(AnswerType.B);
//        assertThat(response.getSolutions().get(2)).isEqualTo(AnswerType.B);
//        assertThat(response.getSolutions().get(3)).isEqualTo(AnswerType.B);
//        assertThat(response.getSolutions().get(4)).isEqualTo(AnswerType.B);
//        assertThat(response.getSolutions().get(5)).isEqualTo(AnswerType.B);
//    }
//
//    @Test
//    void shouldThrowResourceNotFoundExceptionWhenCallCheckTestWithNonExistQuestionId() {
//        //given
//        List<CorrectAnwserCheckRequest> request = Arrays.asList(
//                new CorrectAnwserCheckRequest(1, AnswerType.A),
//                new CorrectAnwserCheckRequest(2, AnswerType.B),
//                new CorrectAnwserCheckRequest(3, AnswerType.C),
//                new CorrectAnwserCheckRequest(4, AnswerType.D),
//                new CorrectAnwserCheckRequest(5, AnswerType.EMPTY)
//        );
//
//        List<CorrectAnswer> correctAnswers = new ArrayList<>();
//        for (long i = 1; i < 5; i++) {
//            CorrectAnswer correctAnswer = new CorrectAnswer();
//            correctAnswer.setQuestionId(i);
//            correctAnswer.setCorrect(AnswerType.B);
//            correctAnswers.add(correctAnswer);
//        }
//
//        when(repository.findAllById(anyIterable())).thenReturn(correctAnswers);
//
//        //when
//        Exception exception = assertThrows(IncorrectResultSizeDataAccessException.class, () -> service.checkTest(request));
//
//        //then
//        assertThat(exception.getMessage()).isEqualTo("Some answers are missing");
//    }
}