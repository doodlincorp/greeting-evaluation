package doodlin.greeting.evaluation.business.domain.evaluation_process.root

import doodlin.greeting.evaluation.business.domain.evaluation_process.sub.EvaluationPaper
import doodlin.greeting.evaluation.business.domain.evaluation_process.sub.Evaluator
import java.time.ZonedDateTime

data class EvaluationGroup(
    val id: Long,
    val evaluationPaperId: Long,
    val applicantId: Long,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}