package doodlin.greeting.concept.adaptor.mysql.repository.jpa

import doodlin.greeting.concept.adaptor.mysql.entity.ConceptEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface ConceptJpaRepository : JpaRepository<ConceptEntity, Int>
