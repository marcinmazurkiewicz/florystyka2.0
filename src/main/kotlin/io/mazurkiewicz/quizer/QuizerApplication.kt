package io.mazurkiewicz.quizer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuizerApplication

fun main(args: Array<String>) {
	runApplication<QuizerApplication>(*args)
}
