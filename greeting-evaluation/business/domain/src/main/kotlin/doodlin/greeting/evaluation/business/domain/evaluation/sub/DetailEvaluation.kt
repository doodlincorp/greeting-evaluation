package doodlin.greeting.evaluation.business.domain.evaluation.sub

import doodlin.greeting.evaluation.business.domain.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreType
import java.time.ZonedDateTime

data class DetailEvaluation(
    val id: Long,
    val evaluationId: Long,
    val order: Int,
    val comment: String?,
    val score: Int?,
    val scoreText: String?,
    val name: String,
    val notice: String?,
    val scoreType: EvaluationScoreType?,
    val useComment: Boolean,
    val scoreShowType: EvaluationScoreShowType,
    val evaluationOptions: List<EvaluationOption>,
    val evaluationWeight: Int?,
    val scoreCriteria: Int,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
