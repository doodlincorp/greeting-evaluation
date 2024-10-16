package doodlin.greeting.concept.adaptor.mysql.entity.mapper

import doodlin.greeting.concept.adaptor.mysql.entity.ConceptEntity
import doodlin.greeting.concept.business.domain.model.Concept

internal fun Concept.toEntity(): ConceptEntity {
    return ConceptEntity(
        id = id,
        type = type,
        idea = idea,
        name = name
    )
}

internal fun ConceptEntity.toModel(): Concept {
    return Concept(
        id = this.id,
        idea = this.idea,
        type = this.type,
        name = this.name
    )
}
