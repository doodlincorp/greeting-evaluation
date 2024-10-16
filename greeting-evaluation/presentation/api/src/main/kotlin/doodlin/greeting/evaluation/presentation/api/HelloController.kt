package doodlin.greeting.concept.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
@Tag(name = "Hello World!", description = "Hello World!")
class HelloController(
    private val conceptManagementUsecase: ConceptManagementUsecase,
) {
    @Operation(summary = "hello concept!", description = "hello concept!")
    @GetMapping
    fun hello(
        @RequestParam(required = true) title: String,
        @RequestParam(required = true) name: String,
    ): String {
        return conceptManagementUsecase.createConcept(name = name, title = title).toString()
    }
}
