package doodlin.greeting.evaluation.business.domain.core.evaluation.reaf

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationStatusType
import java.time.ZonedDateTime

data class EvaluationStatus(
    val id: Long,
    val status: EvaluationStatusType,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)

data class LegacyEvaluationStatus(
    val id: Long,
    val applicantId: Long,
    val status: EvaluationStatusType,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
