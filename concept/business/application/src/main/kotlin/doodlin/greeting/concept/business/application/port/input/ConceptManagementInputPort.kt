package doodlin.greeting.concept.business.application.port.input

import doodlin.greeting.concept.business.application.port.output.ConceptManagementOutputPort
import doodlin.greeting.concept.business.application.usecase.ConceptManagementUsecase
import doodlin.greeting.concept.business.domain.ConceptManagementService
import doodlin.greeting.concept.business.domain.ConceptValidator
import doodlin.greeting.concept.business.domain.model.Concept
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
internal class ConceptManagementInputPort(
    private val conceptManagementOutputPort: ConceptManagementOutputPort,
) : ConceptManagementUsecase {

    @Transactional
    override fun createConcept(name: String, title: String): Concept {
        val concept: Concept = ConceptManagementService.create(name = name, title = title)
        val saved: Concept = conceptManagementOutputPort.save(concept = concept)
        ConceptValidator.validate(concept = saved)
        return saved
    }
}
