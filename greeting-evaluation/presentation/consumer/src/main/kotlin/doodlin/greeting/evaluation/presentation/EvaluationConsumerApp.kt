package doodlin.greeting.concept.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication(
    scanBasePackages = ["doodlin"]
)
@EnableKafka
class EvaluationConsumerApp

fun main(vararg args: String) {
    runApplication<EvaluationConsumerApp>(*args)
}
