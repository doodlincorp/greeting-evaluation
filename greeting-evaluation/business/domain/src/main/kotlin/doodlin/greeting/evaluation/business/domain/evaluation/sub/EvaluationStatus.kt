package doodlin.greeting.evaluation.business.domain.evaluation.sub

import doodlin.greeting.evaluation.business.domain.enums.EvaluationStatusType
import java.time.ZonedDateTime

data class EvaluationStatus(
    val id: Long,
    val applicantId: Long,
    val status: EvaluationStatusType,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}