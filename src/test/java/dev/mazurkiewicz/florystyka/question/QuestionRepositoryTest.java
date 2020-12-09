package dev.mazurkiewicz.florystyka.question;

import dev.mazurkiewicz.florystyka.answer.AnswerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class QuestionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuestionRepository repository;

    @AfterEach
    public void clearEntityManager() {
        entityManager.clear();
    }

    @Test
    void whenGetRandomQuestions_thenReturnQuestionSet() {
        //given
        for (int i = 1; i < 11; i++) {
            entityManager.persist(prepareQuestion(i));
        }

        //when
        Set<Question> result = repository.getRandomQuestions(5);

        //then
        assertThat(result).hasSize(5);
    }

    @Test
    void whenGetEarliestYear_ThenReturnIntValue() {
        //given
        for (int i = 1; i < 11; i++) {
            entityManager.persist(prepareQuestion(i));
        }
        //added Question with null year
        Question question = prepareQuestion(11);
        question.setYear(null);
        entityManager.persist(question);

        //when
        Integer earliestYear = repository.getEarliestYear();

        //then
        assertThat(earliestYear).isEqualTo(2011);
    }

    @Test
    void whenGetEarliestYearInAllNullableYear_ThenReturnZero() {
        //given
        //added Question with null year
        Question question = prepareQuestion(1);
        question.setYear(null);
        entityManager.persist(question);

        //when
        Integer latestYear = repository.getLatestYear();

        //then
        assertThat(latestYear).isNull();
    }

    @Test
    void whenGetLatestYear_ThenReturnIntValue() {
        //given
        for (int i = 1; i < 11; i++) {
            entityManager.persist(prepareQuestion(i));
        }
        //added Question with null year
        Question question = prepareQuestion(11);
        question.setYear(null);
        entityManager.persist(question);

        //when
        Integer latestYear = repository.getLatestYear();

        //then
        assertThat(latestYear).isEqualTo(2020);
    }

    @Test
    void whenGetLatestYearInAllNullableYear_ThenReturnZero() {
        //given
        //added Question with null year
        Question question = prepareQuestion(1);
        question.setYear(null);
        entityManager.persist(question);

        //when
        Integer latestYear = repository.getLatestYear();

        //then
        assertThat(latestYear).isNull();
    }


    private Question prepareQuestion(int i) {
        Question question = new Question();
        question.setAnswerA(String.format("answer A for question %n", i));
        question.setAnswerB(String.format("answer B for question %n", i));
        question.setAnswerC(String.format("answer C for question %n", i));
        question.setAnswerD(String.format("answer D for question %n", i));
        question.setContent(String.format("Question %n content", i));
        AnswerType correct = AnswerType.EMPTY;
        switch (i % 4) {
            case 0:
                correct = AnswerType.A;
                break;
            case 1:
                correct = AnswerType.B;
                break;
            case 2:
                correct = AnswerType.C;
                break;
            case 3:
                correct = AnswerType.D;
                break;
        }

        question.setCorrect(correct);
        if (i % 3 == 0) {
            question.setImg(String.format("img_%n.png", i));
        }
        question.setMonth((i % 12) + 1);
        question.setYear(2010 + i);
        return question;
    }
}