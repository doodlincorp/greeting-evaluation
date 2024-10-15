package doodlin.greeting.concept.adaptor.redis.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "concept")
class ConceptCache(
    @Id
    val id: String,
    val title: String,
    @Indexed
    val key: String,
)
