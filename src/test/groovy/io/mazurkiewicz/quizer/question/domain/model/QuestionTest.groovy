package io.mazurkiewicz.quizer.question.domain.model

import spock.lang.Specification

class QuestionTest extends Specification {

    def 'should return correct when selected answer is correct'() {
        given:
        def testedQuestion = prepareAnswer()
        def selectedAnswer = new SelectedAnswer(AnswerType.C)

        when:
        def answerResult = testedQuestion.checkAnswer(selectedAnswer)

        then:
        answerResult.status == AnswerStatus.CORRECT
        answerResult.correctAnswer ==  AnswerType.C
    }

    def 'should return incorrect when selected answer is incorrect'() {
        given:
        def testedQuestion = prepareAnswer()
        def selectedAnswer = new SelectedAnswer(AnswerType.B)

        when:
        def answerResult = testedQuestion.checkAnswer(selectedAnswer)

        then:
        answerResult.status == AnswerStatus.INCORRECT
        answerResult.correctAnswer ==  AnswerType.C
    }

    def 'should return incorrect when selected answer is empty'() {
        given:
        def testedQuestion = prepareAnswer()
        def selectedAnswer = new SelectedAnswer(AnswerType.EMPTY)

        when:
        def answerResult = testedQuestion.checkAnswer(selectedAnswer)

        then:
        answerResult.status == AnswerStatus.INCORRECT
        answerResult.correctAnswer ==  AnswerType.C
    }

    private Question prepareAnswer() {
        def questionId = new QuestionId(UUID.randomUUID())
        def questionContent = new QuestionContent("Test question #1")
        def questionImage = new QuestionImage("/path/to/img.jpg")
        def answers = [
                new Answer(AnswerType.A, new AnswerContent("question #1 answer A"), AnswerStatus.INCORRECT),
                new Answer(AnswerType.B, new AnswerContent("question #1 answer B"), AnswerStatus.INCORRECT),
                new Answer(AnswerType.C, new AnswerContent("question #1 answer C"), AnswerStatus.CORRECT),
                new Answer(AnswerType.D, new AnswerContent("question #1 answer D"), AnswerStatus.INCORRECT)
        ]
        new Question(questionId, questionContent, answers, questionImage, null)
    }
}
