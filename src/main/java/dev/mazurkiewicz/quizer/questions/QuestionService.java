package dev.mazurkiewicz.quizer.questions;

import dev.mazurkiewicz.quizer.config.QuizerProperties;
import dev.mazurkiewicz.quizer.exception.PdfRenderException;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.pdf.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;
    private final PdfGenerator pdfGenerator;
    private final QuizerProperties properties;

    public QuestionResponse getRandomQuestion() {
        Set<QuestionEntity> questionSet = repository.getRandomQuestions(1);
        if (questionSet.isEmpty())
            throw new EmptyResultDataAccessException("It looks like there is no questions in the database", 1);

        QuestionEntity question = questionSet.iterator().next();
        return mapper.mapEntityToResponse(question);
    }

    public QuestionResponse getQuestionById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", id)));
    }

    public List<QuestionResponse> getQuestionsToTest() {
        Set<QuestionEntity> result = repository.getRandomQuestions(properties.testQuestionsNumber());
        if (result.size() != properties.testQuestionsNumber())
            throw new IncorrectResultSizeDataAccessException("Incorrect questions number", properties.testQuestionsNumber());
        return result.stream()
                .map(mapper::mapEntityToResponse)
                .toList();
    }

    public QuestionNumberResponse countQuestions() {
        long questionNumber = repository.count();
        Integer earliestQuestionYear = repository.getEarliestYear();
        Integer latestQuestionYear = repository.getLatestYear();
        return new QuestionNumberResponse(questionNumber, earliestQuestionYear, latestQuestionYear);
    }

    public byte[] getPdfTest() throws PdfRenderException {
        Set<QuestionEntity> questions = repository.getRandomQuestions(properties.testQuestionsNumber());
        return pdfGenerator.generateTest(questions);
    }


}