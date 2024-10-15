package doodlin.greeting.concept.presentation.batch

import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import kotlin.system.exitProcess

@SpringBootApplication(
    scanBasePackages = ["doodlin"]
)
internal class ConceptBatchSpringBatchApplication

fun main(vararg args: String) {
    val context: ConfigurableApplicationContext = runApplication<ConceptBatchSpringBatchApplication>(*args)
    val exitCode: Int = context.getBean(ExitCodeGenerator::class.java).exitCode
    context.close()
    exitProcess(exitCode)
}
