package doodlin.greeting.evaluation.business.domain.util

import java.time.ZonedDateTime
import java.util.*

// common 모듈을 만들어서 옮겨야 함
object UUIDGenerator {
    private val timeBasedUuidGenerator: TimeBasedGenerator = timeBasedGenerator()

    fun generateUUID(): UUID = timeBasedUuidGenerator.generate()

    fun generateUUID(
        currentTime: ZonedDateTime,
    ): UUID = timeBasedUuidGenerator.construct(
        currentTime.toInstant().toEpochMilli(),
    )
}
