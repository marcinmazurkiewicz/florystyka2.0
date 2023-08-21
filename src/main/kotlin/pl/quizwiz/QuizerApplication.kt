package pl.quizwiz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class QuizerApplication

fun main(args: Array<String>) {
    runApplication<QuizerApplication>(*args)
}
