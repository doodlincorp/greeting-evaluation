package doodlin.greeting.evaluation.presentation.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["doodlin"]
)
class EvaluationApiSpringBootApplication

fun main(vararg args: String) {
    runApplication<EvaluationApiSpringBootApplication>(*args)
}
