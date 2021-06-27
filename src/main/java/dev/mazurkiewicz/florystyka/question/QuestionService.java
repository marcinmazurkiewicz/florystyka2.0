package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.config.ApplicationProperties;
import dev.mazurkiewicz.florystyka.exception.PdfRenderException;
import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import dev.mazurkiewicz.florystyka.pdf.PdfGenerator;
import dev.mazurkiewicz.florystyka.utils.TestTimer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;
    private final PdfGenerator pdfGenerator;
    private final ApplicationProperties properties;

    public QuestionService(QuestionRepository repository, QuestionMapper mapper, PdfGenerator pdfGenerator, ApplicationProperties properties) {
        this.repository = repository;
        this.mapper = mapper;
        this.pdfGenerator = pdfGenerator;
        this.properties = properties;
    }

    public List<QuestionResponse> getAllQuestions() {
        return repository.findAll()
                .stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public QuestionResponse getRandomQuestion() {
        Set<Question> questionSet = repository.getRandomQuestions(1);
        if (questionSet.isEmpty())
            throw new EmptyResultDataAccessException("It looks like there is no questions in the database", 1);

        Question question = questionSet.iterator().next();
        return mapper.mapEntityToResponse(question);
    }

    public QuestionResponse getQuestionById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", id)));
    }

    public TestResponse getQuestionsToTest() {
        Set<Question> result = repository.getRandomQuestions(properties.getTestQuestionsNumber());
        if (result.size() != properties.getTestQuestionsNumber())
            throw new IncorrectResultSizeDataAccessException("Incorrect questions number", properties.getTestQuestionsNumber());
        List<QuestionResponse> questions = result.stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());
        return new TestResponse(new TestTimer(60, 0), questions);
    }

    public QuestionNumberResponse countQuestions() {
        long questionNumber = repository.count();
        Integer earliestQuestionYear = repository.getEarliestYear();
        Integer latestQuestionYear = repository.getLatestYear();
        return new QuestionNumberResponse(questionNumber, earliestQuestionYear, latestQuestionYear);
    }

    public byte[] getPdfTest() throws PdfRenderException {
        Set<Question> questions = repository.getRandomQuestions(properties.getTestQuestionsNumber());
        return pdfGenerator.generateTest(questions);
    }


}
