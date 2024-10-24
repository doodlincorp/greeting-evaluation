package doodlin.greeting.evaluation.business.domain.core.evaluation_history

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationHistoryDomainType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationHistoryType
import java.time.ZonedDateTime

class EvaluationHistory(
    val id: Long,
    val historyType: EvaluationHistoryType,
    val domainType: EvaluationHistoryDomainType,
    val content: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
