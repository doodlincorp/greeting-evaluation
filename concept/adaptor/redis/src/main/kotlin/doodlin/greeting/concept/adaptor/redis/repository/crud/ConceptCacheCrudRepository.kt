package doodlin.greeting.concept.adaptor.redis.repository.crud

import doodlin.greeting.concept.adaptor.redis.entity.ConceptCache
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ConceptCacheCrudRepository : CrudRepository<ConceptCache, String> {
    fun findByKeyEquals(key: String): Optional<ConceptCache>
}
