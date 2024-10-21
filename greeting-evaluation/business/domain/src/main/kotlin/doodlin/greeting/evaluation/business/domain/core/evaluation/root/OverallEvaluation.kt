package doodlin.greeting.evaluation.business.domain.core.evaluation.root

import doodlin.greeting.evaluation.business.domain.core.evaluation.reaf.AverageScore
import doodlin.greeting.evaluation.business.domain.core.evaluation.reaf.Evaluation
import doodlin.greeting.evaluation.business.domain.core.evaluation.reaf.EvaluationStatus
import doodlin.greeting.evaluation.business.domain.support.Applicant
import doodlin.greeting.evaluation.business.domain.support.ProcessOnOpening
import java.time.ZonedDateTime

data class OverallEvaluation(
    val id: Long,
    val evaluations: Set<Evaluation>,
    val evaluationStatus: EvaluationStatus,
    val averageScore: AverageScore,
    val applicantId: Long,
    val processOnOpeningId: Long,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}