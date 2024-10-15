package doodlin.greeting.concept.business.application.port.output

import doodlin.greeting.concept.business.domain.model.Concept

interface ConceptManagementOutputPort {
    fun save(concept: Concept): Concept
}
