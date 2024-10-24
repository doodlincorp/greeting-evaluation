package doodlin.greeting.evaluation.business.domain.core.evaluation.reaf

import java.time.ZonedDateTime

data class AverageScore(
    val id: Long,
    val score: Int?,
    val scoreCount: Int,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)

data class LegacyAverageScore(
    val id: Long,
    val processOnOpeningId: Long,
    val applicantId: Long,
    val score: Int?,
    val scoreCount: Int,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
