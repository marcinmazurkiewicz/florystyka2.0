package dev.mazurkiewicz.quizer.questions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QuestionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private QuestionRepository repository;

    @BeforeEach
    public void prepareEntityManager() {
        for (int i = 1; i < 11; i++) {
            entityManager.persist(prepareQuestion(i));
        }
    }

    @AfterEach
    public void clearEntityManager() {
        entityManager.clear();
    }

    private QuestionEntity prepareQuestion(int questionNumber) {
        QuestionEntity question = new QuestionEntity();
        question.setAnswerA(String.format("answer A for question %d", questionNumber));
        question.setAnswerB(String.format("answer B for question %d", questionNumber));
        question.setAnswerC(String.format("answer C for question %d", questionNumber));
        question.setAnswerD(String.format("answer D for question %d", questionNumber));
        question.setContent(String.format("Question %d content", questionNumber));
        question.setCorrect(AnswerType.A);
        question.setImg(String.format("img_%d.png", questionNumber));
        question.setMonth((questionNumber % 12) + 1);
        question.setYear(2010 + questionNumber);
        return question;
    }

    @Test
    void shouldReturnQuestionSetWhenCallGetRandomQuestion() {
        //given
        //when
        Set<QuestionEntity> result = repository.getRandomQuestions(5);

        //then
        assertThat(result).hasSize(5);
    }

    @Test
    void shouldReturnAllQuestionWhenNotEnoughQuestionsInDb() {
        //given
        long count = repository.count();
        //when
        Set<QuestionEntity> result = repository.getRandomQuestions((int) count * 2);
        //then
        assertThat(result).hasSize((int) count);
    }

    @Test
    void shouldReturnEarliestQuestionYear() {
        //given
        QuestionEntity question = prepareQuestion(20);
        question.setYear(1990);
        entityManager.persist(question);

        //when
        Integer earliestYear = repository.getEarliestYear();

        //then
        assertThat(earliestYear).isEqualTo(1990);
    }

    @Test
    void shouldReturnEarliestQuestionYearWhenIsQuestionWithNullYearInDb() {
        //given
        QuestionEntity question = prepareQuestion(19);
        question.setYear(1990);
        entityManager.persist(question);
        QuestionEntity question2 = prepareQuestion(20);
        question2.setYear(null);
        entityManager.persist(question2);

        //when
        Integer earliestYear = repository.getEarliestYear();

        //then
        assertThat(earliestYear).isEqualTo(1990);
    }

    @Test
    void shouldReturnNullAsEarliestYearWhenDbIsEmpty() {
        //given
        entityManager.clear();

        //when
        Integer earliestYear = repository.getEarliestYear();

        //then
        assertThat(earliestYear).isNull();
    }

    @Test
    void shouldReturnLatestQuestionYear() {
        //given
        QuestionEntity question = prepareQuestion(20);
        question.setYear(2077);
        entityManager.persist(question);

        //when
        Integer earliestYear = repository.getLatestYear();

        //then
        assertThat(earliestYear).isEqualTo(2077);
    }

    @Test
    void shouldReturnLatestQuestionYearWhenIsQuestionWithNullYearInDb() {
        //given
        QuestionEntity question = prepareQuestion(19);
        question.setYear(2077);
        entityManager.persist(question);
        QuestionEntity question2 = prepareQuestion(20);
        question2.setYear(null);
        entityManager.persist(question2);

        //when
        Integer earliestYear = repository.getLatestYear();

        //then
        assertThat(earliestYear).isEqualTo(2077);
    }

    @Test
    void shouldReturnNullAsLatestYearWhenDbIsEmpty() {
        //given
        entityManager.clear();

        //when
        Integer earliestYear = repository.getLatestYear();

        //then
        assertThat(earliestYear).isNull();
    }
}