package doodlin.greeting.concept.business.domain

import doodlin.greeting.concept.business.domain.model.Concept
import doodlin.greeting.concept.business.domain.model.constants.ConceptTypes

class ConceptValidator {
    companion object {

        @JvmStatic
        fun validate(concept: Concept) {
            concept.requireIdNotNull()
            when (concept.type) {
                ConceptTypes.EXPERT -> throw IllegalStateException()
                else -> {}
            }
        }
    }
}
