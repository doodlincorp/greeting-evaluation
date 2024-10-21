package doodlin.greeting.evaluation.business.domain.core.evaluation_paper.reaf

import java.time.ZonedDateTime

data class Evaluator(
    val id: Long,
    val userId: Long,
    val evaluationPaperId: Long,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}