package dev.mazurkiewicz.quizer.questions;

import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import dev.mazurkiewicz.quizer.exception.PdfRenderException;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.pdf.PdfGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;
    private final PdfGenerator pdfGenerator;
    private final QuizerConfiguration quizerConfiguration;

    public QuestionResponse getRandomQuestion() {
        Set<QuestionEntity> questionSet = repository.getRandomQuestions(1);
        if (questionSet.isEmpty()) {
            log.error("Database is empty");
            throw new EmptyResultDataAccessException("It looks like there is no questions in the database", 1);
        }
        QuestionEntity question = questionSet.iterator().next();
        return mapper.mapEntityToResponse(question);
    }

    public QuestionResponse getQuestionById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", id)));
    }

    public ExamResponse getExamData() {
        Set<QuestionEntity> result = repository.getRandomQuestions(quizerConfiguration.examQuestionsNumber());
        if (result.size() != quizerConfiguration.examQuestionsNumber()) {
            log.error("Problem with getting questions from database. Needed: {}, received: {}",
                    quizerConfiguration.examQuestionsNumber(), result.size());
            throw new IncorrectResultSizeDataAccessException("Incorrect questions number", quizerConfiguration.examQuestionsNumber());
        }
        List<QuestionResponse> questions = result.stream()
                .map(mapper::mapEntityToResponse)
                .toList();
        return new ExamResponse(ExamTimer.fromSeconds(quizerConfiguration.examTimeInSeconds()), questions);
    }

    public QuestionInfoResponse getQuestionsInfo() {
        long questionNumber = repository.count();
        Integer earliestQuestionYear = repository.getEarliestYear();
        Integer latestQuestionYear = repository.getLatestYear();
        return new QuestionInfoResponse(questionNumber, earliestQuestionYear, latestQuestionYear);
    }

    public byte[] getPdfTest() throws PdfRenderException {
        Set<QuestionEntity> questions = repository.getRandomQuestions(quizerConfiguration.examQuestionsNumber());
        return pdfGenerator.generateTest(questions);
    }


}