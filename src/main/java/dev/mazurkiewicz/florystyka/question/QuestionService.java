package dev.mazurkiewicz.florystyka.question;


import dev.mazurkiewicz.florystyka.exception.ResourceNotFoundException;
import dev.mazurkiewicz.florystyka.pdf.PdfGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;
    private final PdfGenerator pdfGenerator;

    @Value("${dev.mazurkiewicz.florystyka.testQuestionsNumber}")
    private int questionToTest;

    public QuestionService(QuestionRepository repository, QuestionMapper mapper, PdfGenerator pdfGenerator) {
        this.repository = repository;
        this.mapper = mapper;
        this.pdfGenerator = pdfGenerator;
    }

    public QuestionResponse getRandomQuestion() {
        Question question = repository.getRandomQuestions(1).get(0);
        return mapper.mapEntityToResponse(question);
    }

    public QuestionResponse getQuestionById(Integer id) {
        return repository
                .findById(id)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", id)));
    }

    public List<QuestionResponse> getQuestionsToTest() {
        return repository.getRandomQuestions(questionToTest)
                .stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public QuestionNumberResponse countQuestions() {
        long questionNumber = repository.count();
        int earliestQuestionYear = repository.getEarliestYear();
        int latestQuestionYear = repository.getLatestYear();
        return new QuestionNumberResponse(questionNumber, earliestQuestionYear, latestQuestionYear);
    }

    public byte[] getPdfTest() {
        List<QuestionResponse> questions = getQuestionsToTest();
        return pdfGenerator.generateTest(questions);
    }


}
