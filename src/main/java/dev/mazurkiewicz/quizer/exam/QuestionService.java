package dev.mazurkiewicz.quizer.exam;

import dev.mazurkiewicz.quizer.config.QuizerConfiguration;
import dev.mazurkiewicz.quizer.exam.application.ExamResponse;
import dev.mazurkiewicz.quizer.exception.PdfRenderException;
import dev.mazurkiewicz.quizer.pdf.PdfGenerator;
import dev.mazurkiewicz.quizer.question.infrastructure.db.JpaQuestionRepository;
import dev.mazurkiewicz.quizer.question.infrastructure.db.QuestionDBEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final JpaQuestionRepository repository;
    private final PdfGenerator pdfGenerator;
    private final QuizerConfiguration quizerConfiguration;


    public ExamResponse getExamData() {
        Set<QuestionDBEntity> result = repository.getRandomQuestions(quizerConfiguration.examQuestionsNumber());
        if (result.size() != quizerConfiguration.examQuestionsNumber()) {
            log.error("Problem with getting questions from database. Needed: {}, received: {}",
                    quizerConfiguration.examQuestionsNumber(), result.size());
            throw new IncorrectResultSizeDataAccessException("Incorrect questions number", quizerConfiguration.examQuestionsNumber());
        }
//        List<QuestionResponse> questions = result.stream()
//                .map(QuestionResponse.::mapEntityToResponse)
//                .toList();
//        return new ExamResponse(ExamTimer.fromSeconds(quizerConfiguration.examTimeInSeconds()), questions);
        return null;
    }


    public byte[] getPdfTest() throws PdfRenderException {
        Set<QuestionDBEntity> questions = repository.getRandomQuestions(quizerConfiguration.examQuestionsNumber());
        return pdfGenerator.generateTest(questions);
    }


}