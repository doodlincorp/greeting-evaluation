package doodlin.greeting.concept.business.domain.model

import doodlin.greeting.concept.business.domain.model.constants.ConceptTypes
import doodlin.greeting.concept.business.domain.model.struct.Idea
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class Concept(
    @field:NotNull val id: Int = 0,
    @field:NotNull @field:Size(min = 1, max = 255) val name: String,
    @field:NotNull val idea: Idea,
    @field:NotNull val type: ConceptTypes,
) {
    internal fun requireIdNotNull() {
        requireNotNull(id)
    }
}
