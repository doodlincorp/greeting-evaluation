package doodlin.greeting.concept.adaptor.redis.entity.mapper

import doodlin.greeting.concept.business.domain.model.Concept
import doodlin.greeting.concept.business.domain.model.constants.ConceptTypes
import doodlin.greeting.concept.business.domain.model.struct.Idea
import java.util.*

internal fun Concept.toCache(key: String): ConceptCache {
    return ConceptCache(
        id = UUID.randomUUID().toString(),
        title = this.idea.title,
        key = key
    )
}

internal fun ConceptCache.toModel(): Concept {
    return Concept(
        id = 0,
        name = "name",
        idea = Idea(this.title),
        type = ConceptTypes.EXPERT
    )
}
