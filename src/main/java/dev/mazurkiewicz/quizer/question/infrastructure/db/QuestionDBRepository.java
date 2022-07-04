package dev.mazurkiewicz.quizer.question.infrastructure.db;

import dev.mazurkiewicz.quizer.exam.domain.port.QuestionNumber;
import dev.mazurkiewicz.quizer.exception.ResourceNotFoundException;
import dev.mazurkiewicz.quizer.question.domain.model.Question;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionId;
import dev.mazurkiewicz.quizer.question.domain.model.QuestionInfo;
import dev.mazurkiewicz.quizer.question.domain.port.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestionDBRepository implements QuestionRepository {

    private final JpaQuestionRepository repository;

    @Override
    public Question getQuestionById(QuestionId id) {
        return repository.findById(id.value())
                .map(QuestionDBEntity::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Question with id %d doesn't exist", id.value())));
    }

    @Override
    public List<Question> getRandomQuestions(QuestionNumber questionNumber) {
        List<Question> questions = repository.getRandomQuestions(questionNumber.value())
                .stream()
                .map(QuestionDBEntity::toDomain)
                .toList();
        if (questions.size() != questionNumber.value()) {
            log.error("Problem with getting questions from database. Needed: {}, received: {}",
                    questionNumber.value(), questions.size());
            throw new IncorrectResultSizeDataAccessException("Incorrect questions number", questionNumber.value());
        }
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> randomQuestions = getRandomQuestions(new QuestionNumber(1));
        return randomQuestions.stream()
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Database is empty");
                    return new EmptyResultDataAccessException("It looks like there is no questions in the database", 1);
                });
    }


    @Override
    public QuestionInfo getQuestionInfo() {
        long questionNumber = repository.count();
        Integer earliestQuestionYear = repository.getEarliestYear();
        Integer latestQuestionYear = repository.getLatestYear();
        return new QuestionInfo(questionNumber, earliestQuestionYear, latestQuestionYear);
    }

}
