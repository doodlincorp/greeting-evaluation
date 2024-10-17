package doodlin.greeting.evaluation.business.domain.evaluation_history

import doodlin.greeting.evaluation.business.domain.enums.EvaluationHistoryType
import java.time.ZonedDateTime

class EvaluationHistory(
    val id: Long,
    val historyType: EvaluationHistoryType,
    val content: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}