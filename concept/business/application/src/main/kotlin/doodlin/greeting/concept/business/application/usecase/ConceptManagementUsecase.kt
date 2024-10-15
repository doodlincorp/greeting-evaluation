package doodlin.greeting.concept.business.application.usecase

import doodlin.greeting.concept.business.domain.model.Concept

interface ConceptManagementUsecase {
    fun createConcept(name: String, title: String): Concept
}
