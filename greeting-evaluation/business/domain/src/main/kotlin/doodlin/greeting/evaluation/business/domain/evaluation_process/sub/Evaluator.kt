package doodlin.greeting.evaluation.business.domain.evaluation_process.sub

import java.time.ZonedDateTime

data class Evaluator(
    val id: Long,
    val userId: Long,
    val evaluationGroupId: Long,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}