package doodlin.greeting.evaluation.business.domain.core.evaluation.reaf

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreType
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
