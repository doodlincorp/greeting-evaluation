package doodlin.greeting.concept.business.domain.model.struct

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
data class Idea(
    @field:NotNull
    val title: String,
)
