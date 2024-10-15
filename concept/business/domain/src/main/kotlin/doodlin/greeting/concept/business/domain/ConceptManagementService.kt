package doodlin.greeting.concept.business.domain

import doodlin.greeting.concept.business.domain.model.Concept
import doodlin.greeting.concept.business.domain.model.constants.ConceptTypes
import doodlin.greeting.concept.business.domain.model.struct.Idea

class ConceptManagementService {
    companion object {
        @JvmStatic
        fun create(name: String, title: String): Concept {
            return Concept(
                id = 0,
                idea = Idea(
                    title = title
                ), type = ConceptTypes.NORMAL,
                name = name
            )
        }
    }
}
