package doodlin.greeting.concept.presentation.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["doodlin"]
)
class ConceptApiSpringBootApplication

fun main(vararg args: String) {
    runApplication<ConceptApiSpringBootApplication>(*args)
}
