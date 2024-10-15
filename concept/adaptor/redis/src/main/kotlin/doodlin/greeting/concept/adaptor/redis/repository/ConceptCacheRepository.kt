package doodlin.greeting.concept.adaptor.redis.repository

import doodlin.greeting.concept.adaptor.redis.entity.mapper.toCache
import doodlin.greeting.concept.adaptor.redis.entity.mapper.toModel
import doodlin.greeting.concept.adaptor.redis.repository.crud.ConceptCacheCrudRepository
import doodlin.greeting.concept.business.application.port.output.ConceptCacheOutputPort
import doodlin.greeting.concept.business.domain.model.Concept
import org.springframework.stereotype.Repository

@Repository
internal class ConceptCacheRepository(
    private val conceptCacheCrudRepository: ConceptCacheCrudRepository,
) : ConceptCacheOutputPort {
    override fun append(key: String, payload: Concept) {
        conceptCacheCrudRepository.save(
            payload.toCache(key = key)
        )
    }

    override fun read(key: String): Concept {
        return conceptCacheCrudRepository.findByKeyEquals(key = key)
            .orElseThrow { IllegalArgumentException() }
            .toModel()
    }
}
