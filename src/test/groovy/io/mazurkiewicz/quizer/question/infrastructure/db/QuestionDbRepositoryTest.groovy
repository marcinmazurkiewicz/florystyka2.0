package io.mazurkiewicz.quizer.question.infrastructure.db

import io.mazurkiewicz.quizer.question.domain.model.AnswerStatus
import io.mazurkiewicz.quizer.question.domain.model.AnswerType
import io.mazurkiewicz.quizer.question.domain.model.QuestionId
import io.mazurkiewicz.quizer.question.domain.port.QuestionNumber
import io.mazurkiewicz.quizer.question.infrastructure.QuestionNotFoundException
import io.mazurkiewicz.quizer.question.infrastructure.QuestionNumberIsIncorrectException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class QuestionDbRepositoryTest extends Specification {

    @Autowired
    @Subject
    private QuestionDbRepository questionDbRepository

    def 'should return random question'() {
        when:
        def question = questionDbRepository.findRandomQuestion()

        then:
        question != null
        !question.content.value.isEmpty()
        question.id.value != null
        question.answers.size() == 4
    }

    def 'should return list of random questions'() {
        when:
        def questions = questionDbRepository.findRandomQuestions(new QuestionNumber(5))

        then:
        questions.size() == 5
        questions.forEach {assert !it.content.value.isEmpty() && it.id.value != null && it.answers.size() == 4 }
    }

    def 'should throw exception when result list is smaller than searching questions number'() {
        when:
        questionDbRepository.findRandomQuestions(new QuestionNumber(15))

        then:
        thrown(QuestionNumberIsIncorrectException)
    }

    def 'should return question with passed id'() {
        given:
        def questionPublicId = UUID.fromString('a01423fa-82ed-413c-934b-09863a9daed0')
        def questionContent = 'question #2'
        def answerA = 'answer A in question #2'
        def answerB = 'answer B in question #2'
        def answerC = 'answer C in question #2'
        def answerD = 'answer D in question #2'

        when:
        def question = questionDbRepository.findQuestionById(new QuestionId(questionPublicId))

        then:
        question != null
        question.id.value == questionPublicId
        question.content.value == questionContent
        question.answers.find { it.type == AnswerType.A && it.status == AnswerStatus.INCORRECT }.content.value == answerA
        question.answers.find { it.type == AnswerType.B && it.status == AnswerStatus.INCORRECT }.content.value == answerB
        question.answers.find { it.type == AnswerType.C && it.status == AnswerStatus.INCORRECT }.content.value == answerC
        question.answers.find { it.type == AnswerType.D && it.status == AnswerStatus.CORRECT }.content.value == answerD
    }

    def 'should throw exception when question with passed id not exist'() {
        when:
        questionDbRepository.findQuestionById(new QuestionId(UUID.randomUUID()))

        then:
        thrown(QuestionNotFoundException)
    }

    def 'should return questions with passed ids'() {
        given:
        def questionNo2PublicId = UUID.fromString('a01423fa-82ed-413c-934b-09863a9daed0')
        def questionNo7PublicId = UUID.fromString('f7a9350b-7a7c-4032-827c-19bd17520fa1')
        def questionContent = 'question #%d'
        def answerA = 'answer A in question #%d'
        def answerB = 'answer B in question #%d'
        def answerC = 'answer C in question #%d'
        def answerD = 'answer D in question #%d'

        when:
        def questions = questionDbRepository.findQuestions([new QuestionId(questionNo2PublicId), new QuestionId(questionNo7PublicId)])

        then:
        questions.size() == 2
        def questionNo2 = questions.find { it.id.value == questionNo2PublicId }
        questionNo2 != null
        questionNo2.content.value == questionContent.formatted(2)
        questionNo2.answers.find { it.type == AnswerType.A && it.status == AnswerStatus.INCORRECT }.content.value == answerA.formatted(2)
        questionNo2.answers.find { it.type == AnswerType.B && it.status == AnswerStatus.INCORRECT }.content.value == answerB.formatted(2)
        questionNo2.answers.find { it.type == AnswerType.C && it.status == AnswerStatus.INCORRECT }.content.value == answerC.formatted(2)
        questionNo2.answers.find { it.type == AnswerType.D && it.status == AnswerStatus.CORRECT }.content.value == answerD.formatted(2)

        def questionNo7 = questions.find { it.id.value == questionNo7PublicId }
        questionNo7 != null
        questionNo7.content.value == questionContent.formatted(7)
        questionNo7.answers.find { it.type == AnswerType.A && it.status == AnswerStatus.INCORRECT }.content.value == answerA.formatted(7)
        questionNo7.answers.find { it.type == AnswerType.B && it.status == AnswerStatus.INCORRECT }.content.value == answerB.formatted(7)
        questionNo7.answers.find { it.type == AnswerType.C && it.status == AnswerStatus.INCORRECT }.content.value == answerC.formatted(7)
        questionNo7.answers.find { it.type == AnswerType.D && it.status == AnswerStatus.CORRECT }.content.value == answerD.formatted(7)

    }
}
