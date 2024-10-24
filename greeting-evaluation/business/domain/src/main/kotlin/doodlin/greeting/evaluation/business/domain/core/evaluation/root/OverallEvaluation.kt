package doodlin.greeting.evaluation.business.domain.core.evaluation.root

import doodlin.greeting.evaluation.business.domain.constants.EVALUATION_AVERAGE_CRITERIA
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreCalculationType
import doodlin.greeting.evaluation.business.domain.core.evaluation.reaf.AverageScore
import doodlin.greeting.evaluation.business.domain.core.evaluation.reaf.Evaluation
import doodlin.greeting.evaluation.business.domain.core.evaluation.reaf.EvaluationStatus
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
    fun calculateScore(
        defaultScore: Int,
        scores: List<Int>,
        weights: List<Int>,
        scoreCalculationType: EvaluationScoreCalculationType
    ): Int = when (scoreCalculationType) {
        EvaluationScoreCalculationType.MANUAL -> this.calculateByManualType(defaultScore)

        EvaluationScoreCalculationType.AVERAGE_SCORE -> this.calculateByAverageType(scores)

        EvaluationScoreCalculationType.TOTAL_SCORE -> this.calculateByTotalType(scores)

        EvaluationScoreCalculationType.WEIGHT_SCORE -> {
            this.calculateByWeightType(
                scores = scores,
                weights = weights
            )
        }
    }

    private fun calculateByManualType(score: Int): Int = score

    private fun calculateByAverageType(scores: List<Int>): Int = scores.sum() / scores.size

    private fun calculateByTotalType(scores: List<Int>): Int = scores.sum()

    private fun calculateByWeightType(
        scores: List<Int>,
        weights: List<Int>
    ): Int = scores.mapIndexed { index, score -> (score * weights[index]).div(EVALUATION_AVERAGE_CRITERIA) }
        .sum()
        .toInt(
}
