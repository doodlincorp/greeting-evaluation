package doodlin.greeting.evaluation.business.domain.evaluation.sub

import java.time.ZonedDateTime

data class AverageScore(
    val id: Long,
    val processOnOpeningId: Long,
    val applicantId: Long,
    val score: Int?,
    val scoreCount: Int,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}