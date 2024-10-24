package doodlin.greeting.evaluation.business.domain.core.review.struct

import java.time.ZonedDateTime
import java.util.*

data class GetReviewSummaryQuery(
    val reviewId: UUID,
    val projectId: Long,
    val notice: String,
    val completedAt: ZonedDateTime? = null,
    val createdAt: ZonedDateTime,
    val deletedAt: ZonedDateTime? = null,
    val pendingCount: Long = 0,
    val acceptedCount: Long = 0,
    val heldCount: Long = 0,
    val rejectedCount: Long = 0,
)
