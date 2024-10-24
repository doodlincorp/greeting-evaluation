package doodlin.greeting.evaluation.business.domain.core.review

import java.time.ZonedDateTime
import java.util.*

data class ReviewCommentDomain(
    val id: Long = 0,
    val reviewId: UUID,
    val creatorId: Long,
    val creatorName: String,
    val creatorEmail: String,
    val comment: String,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime = createdAt,
    val deletedAt: ZonedDateTime? = null,
) {
    val isActive: Boolean
        get() = deletedAt == null

    fun delete(newDeletedAt: ZonedDateTime): ReviewCommentDomain =
        takeIf { isActive }
            ?.copy(deletedAt = newDeletedAt)
            ?: this

    fun modifyComment(newComment: String, newUpdatedAt: ZonedDateTime): ReviewCommentDomain =
        takeIf { isActive }
            ?.copy(comment = newComment, updatedAt = newUpdatedAt)
            ?: this
}
