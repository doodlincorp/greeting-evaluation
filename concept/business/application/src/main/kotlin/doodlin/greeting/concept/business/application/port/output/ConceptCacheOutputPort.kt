package doodlin.greeting.concept.business.application.port.output

import doodlin.greeting.concept.business.domain.model.Concept

interface ConceptCacheOutputPort {
    fun append(key: String, payload: Concept)
    fun read(key: String): Concept
}
