package dev.mazurkiewicz.florystyka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlorystykaApplication

fun main(args: Array<String>) {
	runApplication<FlorystykaApplication>(*args)
}
