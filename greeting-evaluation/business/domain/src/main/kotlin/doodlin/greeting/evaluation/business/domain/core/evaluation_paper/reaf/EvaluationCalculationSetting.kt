package doodlin.greeting.evaluation.business.domain.core.evaluation_paper.reaf

import doodlin.greeting.evaluation.business.domain.constants.EVALUATION_AVERAGE_CRITERIA
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreCalculationType
import java.time.ZonedDateTime

data class EvaluationCalculationSetting(
    // 상세 평가표 계산 id
    val id: Long,
    // 상세 평가표 계산 최상위 채용단계 id
    val processOnOpeningId: Long,
    // 상세 평가표 계산 방식
    val scoreCalculationType: EvaluationScoreCalculationType,
    // 상세 평가표 계산 생성일
    val createDate: ZonedDateTime,
    // 상세 평가표 계산 수정일
    val updateDate: ZonedDateTime,
    // 상세 평가표 계산 삭제일
    val deletedAt: ZonedDateTime?
) {
    fun calculateScore(
        defaultScore: Int,
        scores: List<Int>,
        weights: List<Int>
    ): Int = when (this.scoreCalculationType) {
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
        .toInt()
}
