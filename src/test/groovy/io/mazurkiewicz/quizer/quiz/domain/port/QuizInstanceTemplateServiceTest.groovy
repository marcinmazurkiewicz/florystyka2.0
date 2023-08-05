package io.mazurkiewicz.quizer.quiz.domain.port

import io.mazurkiewicz.quizer.quiz.domain.model.TemplatePassThreshold
import io.mazurkiewicz.quizer.quiz.domain.model.DrawType
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateAccessType
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateAuthor
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateDrawSettings
import io.mazurkiewicz.quizer.quiz.domain.model.TemplateName
import spock.lang.Specification
import spock.lang.Subject

class QuizInstanceTemplateServiceTest extends Specification {

    private QuizTemplateRepository repository = new InMemoryTemplateRepository()

    @Subject
    private QuizTemplateService quizTemplateService = new QuizTemplateService(repository)

    def 'should create new quiz and return id'() {
        given:
        def templateName = new TemplateName("test template")
        def templateAuthor = new TemplateAuthor(UUID.randomUUID())
        def accessType = TemplateAccessType.PUBLIC
        def drawSettings = new TemplateDrawSettings(DrawType.NONE, 0)
        def defaultPercentPassThreshold = new TemplatePassThreshold(51)

        when:
        def templateId = quizTemplateService.createTemplate(
                templateName, templateAuthor, accessType, drawSettings, defaultPercentPassThreshold
        )

        then:
        templateId != null
    }

    def 'should find and return created template'() {
        given:
        def templateName = new TemplateName("test template to found")
        def templateAuthor = new TemplateAuthor(UUID.randomUUID())
        def accessType = TemplateAccessType.PUBLIC
        def shuffleType = new TemplateDrawSettings(DrawType.NONE, 0)
        def defaultPercentPassThreshold = new TemplatePassThreshold(51)

        and: 'create new template'
        def templateId = quizTemplateService.createTemplate(templateName, templateAuthor, accessType, shuffleType, defaultPercentPassThreshold)

        when:
        def searchedTemplate = quizTemplateService.findTemplate(templateId)

        then:
        searchedTemplate.name.value == templateName.value
        searchedTemplate.author.authorId == templateAuthor.authorId
        searchedTemplate.accessType == accessType
        searchedTemplate.defaultDrawSettings.type == shuffleType.type
        searchedTemplate.defaultDrawSettings.questionNumber == shuffleType.questionNumber
        searchedTemplate.templatePassThreshold.percentage == defaultPercentPassThreshold.percentage
    }
}