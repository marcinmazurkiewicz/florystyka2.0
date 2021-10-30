package dev.mazurkiewicz.quizer.question;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
//
//    @Mock
//    private QuestionRepository repository;
//
//    @Mock
//    private QuestionMapper mapper;
//
//    @Mock
//    private PdfGenerator pdfGenerator;
//
//    private ApplicationProperties properties = new ApplicationProperties(10, "", "");
//    private QuestionService service;
//
//    @BeforeEach
//    public void setup() {
//        service = new QuestionService(repository, mapper, pdfGenerator, properties);
//    }
//
//    private Question prepareQuestion(long questionNo) {
//        Question question = new Question();
//        question.setId(questionNo);
//        question.setAnswerA(String.format("answer A for question %d", questionNo));
//        question.setAnswerB(String.format("answer B for question %d", questionNo));
//        question.setAnswerC(String.format("answer C for question %d", questionNo));
//        question.setAnswerD(String.format("answer D for question %d", questionNo));
//        question.setContent(String.format("Question %d content", questionNo));
//        question.setCorrect(AnswerType.C);
//        question.setImg(String.format("img_%d.png", questionNo));
//        question.setMonth(1);
//        question.setYear(2010);
//        return question;
//    }
//
//    @Test
//    void shouldReturnOneRandomQuestionResponse() {
//        //given
//        Question question = prepareQuestion(1);
//        QuestionResponse response = new QuestionResponse(
//                question.getId(),
//                question.getContent(),
//                Arrays.asList(new Answer(AnswerType.A, question.getAnswerA()),
//                        new Answer(AnswerType.B, question.getAnswerB()),
//                        new Answer(AnswerType.C, question.getAnswerC()),
//                        new Answer(AnswerType.D, question.getAnswerD())),
//                question.getImg()
//        );
//        when(repository.getRandomQuestions(1)).thenReturn(Set.of(question));
//        when(mapper.mapEntityToResponse(question)).thenReturn(response);
//
//        //when
//        QuestionResponse result = service.getRandomQuestion();
//
//        //then
//        verify(repository, times(1)).getRandomQuestions(1);
//        verify(mapper, times(1)).mapEntityToResponse(question);
//        assertThat(result).isEqualTo(response);
//    }
//
//    @Test
//    void shouldThrowEmptyResultDataAccessExceptionWhenRepositoryReturnEmptySet() {
//        //given
//        when(repository.getRandomQuestions(1)).thenReturn(Set.of());
//
//        //when
//        //then
//        assertThrows(EmptyResultDataAccessException.class, () -> service.getRandomQuestion());
//        verify(repository, times(1)).getRandomQuestions(1);
//        verify(mapper, never()).mapEntityToResponse(any());
//    }
//
//    @Test
//    void shouldReturnQuestionResponseWhenQuestionWithIdExistInRepository() {
//        Question question = prepareQuestion(2);
//        QuestionResponse response = new QuestionResponse(
//                question.getId(),
//                question.getContent(),
//                Arrays.asList(new Answer(AnswerType.A, question.getAnswerA()),
//                        new Answer(AnswerType.B, question.getAnswerB()),
//                        new Answer(AnswerType.C, question.getAnswerC()),
//                        new Answer(AnswerType.D, question.getAnswerD())),
//                question.getImg()
//        );
//        when(repository.findById(2)).thenReturn(Optional.of(question));
//        when(mapper.mapEntityToResponse(question)).thenReturn(response);
//
//        //when
//        QuestionResponse result = service.getQuestionById(2);
//
//        //then
//        verify(repository, times(1)).findById(2);
//        verify(mapper, times(1)).mapEntityToResponse(question);
//        assertThat(result).isEqualTo(response);
//    }
//
//    @Test
//    void shouldThrowResourceNotFoundExceptionWhenQuestionWithIdNotExist() {
//        //given
//        when(repository.findById(3)).thenReturn(Optional.empty());
//
//        //when
//        Exception exception = assertThrows(ResourceNotFoundException.class, () -> service.getQuestionById(3));
//
//        //then
//        verify(repository, times(1)).findById(3);
//        assertThat(exception.getMessage()).isEqualTo("Question with id 3 doesn't exist");
//    }
//
//    @Test
//    void shouldReturnQuestionNumberResponseWhenCountQuestions() {
//        //given
//        long countResult = 100L;
//        Integer earliestYearResult = 2010;
//        Integer latestYearResult = 2020;
//        when(repository.count()).thenReturn(countResult);
//        when(repository.getEarliestYear()).thenReturn(earliestYearResult);
//        when(repository.getLatestYear()).thenReturn(latestYearResult);
//        QuestionNumberResponse expected = new QuestionNumberResponse(countResult, earliestYearResult, latestYearResult);
//
//        //when
//        QuestionNumberResponse result = service.countQuestions();
//
//        //then
//        verify(repository, times(1)).count();
//        verify(repository, times(1)).getEarliestYear();
//        verify(repository, times(1)).getLatestYear();
//        assertThat(expected).isEqualTo(result);
//    }
//
//    @Test
//    void shouldReturnEmptyQuestionNumberResponseWhenRepositoryReturnNulls() {
//        //given
//        long countResult = 0;
//        Integer earliestYearResult = null;
//        Integer latestYearResult = null;
//        when(repository.count()).thenReturn(countResult);
//        when(repository.getEarliestYear()).thenReturn(earliestYearResult);
//        when(repository.getLatestYear()).thenReturn(latestYearResult);
//        QuestionNumberResponse expected = new QuestionNumberResponse(countResult, earliestYearResult, latestYearResult);
//
//        //when
//        QuestionNumberResponse result = service.countQuestions();
//
//        //then
//        verify(repository, times(1)).count();
//        verify(repository, times(1)).getEarliestYear();
//        verify(repository, times(1)).getLatestYear();
//        assertThat(expected).isEqualTo(result);
//    }
//
//    @Test
//    void shouldReturnByteArrayWhenGetPdfTest() throws PdfRenderException {
//        Set<Question> questions = new HashSet<>();
//        for (int i = 1; i < properties.getTestQuestionsNumber() + 1; i++) {
//            questions.add(prepareQuestion(i));
//        }
//        byte[] expected = "mock result".getBytes();
//        when(repository.getRandomQuestions(properties.getTestQuestionsNumber())).thenReturn(questions);
//        when(pdfGenerator.generateTest(questions)).thenReturn(expected);
//
//        //when
//        byte[] result = service.getPdfTest();
//
//        //then
//        verify(repository, times(1)).getRandomQuestions(properties.getTestQuestionsNumber());
//        assertThat(result).isEqualTo(expected);
//    }
//
//    @Test
//    void shouldReturnQuestionResponseListWhenGetQuestionsToTest() {
//        //given
//        Set<Question> questions = new HashSet<>();
//        for (int i = 1; i < properties.getTestQuestionsNumber() + 1; i++) {
//            questions.add(prepareQuestion(i));
//        }
//        QuestionResponse response = new QuestionResponse(1L, "mock content", new ArrayList<>(), "");
//        when(repository.getRandomQuestions(properties.getTestQuestionsNumber())).thenReturn(questions);
//        when(mapper.mapEntityToResponse(ArgumentMatchers.any(Question.class))).thenReturn(response);
//
//        //when
//        TestResponse result = service.getQuestionsToTest();
//
//        //then
//        verify(repository, times(1)).getRandomQuestions(properties.getTestQuestionsNumber());
//        verify(mapper, times(properties.getTestQuestionsNumber())).mapEntityToResponse(ArgumentMatchers.any(Question.class));
//        assertThat(result.getQuestions()).hasSize(properties.getTestQuestionsNumber());
//    }
//
//    @Test
//    void shouldThrowIncorrectResultSizeDataAccessExceptionWhenRepositoryReturnNotEnoughQuestions() {
//        //given
//        Set<Question> questions = new HashSet<>();
//        for (int i = 1; i < properties.getTestQuestionsNumber(); i++) {
//            questions.add(prepareQuestion(i));
//        }
//        when(repository.getRandomQuestions(properties.getTestQuestionsNumber())).thenReturn(questions);
//
//        //when
//        //then
//        assertThrows(IncorrectResultSizeDataAccessException.class, () -> service.getQuestionsToTest());
//        verify(repository, times(1)).getRandomQuestions(properties.getTestQuestionsNumber());
//        verify(mapper, never()).mapEntityToResponse(ArgumentMatchers.any(Question.class));
//    }

}