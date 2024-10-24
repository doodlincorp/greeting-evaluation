package doodlin.greeting.evaluation.business.domain.core.review

import doodlin.greeting.greetingtrmserver.util.UUIDGenerator
import java.time.ZonedDateTime
import java.util.*

data class ReviewDomain(
    val id: Long = 0,

    val workspaceId: Long,

    val reviewId: UUID = UUIDGenerator.generateUUID(),

    val notice: String,

    val requesterId: Long,

    val candidateId: Long,

    val projectId: Long,

    val completedAt: ZonedDateTime? = null,

    val createdAt: ZonedDateTime = ZonedDateTime.now(),

    val updatedAt: ZonedDateTime = ZonedDateTime.now(),

    val deletedAt: ZonedDateTime? = null,
) {

    val isNotCompleted: Boolean
        get() = completedAt == null

    fun complete(newCompletedAt: ZonedDateTime): ReviewDomain {
        return if (isNotCompleted) copy(completedAt = newCompletedAt)
        else this
    }
}
