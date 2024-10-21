package doodlin.greeting.evaluation.business.domain.core.evaluation_paper.reaf

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreType
import java.time.ZonedDateTime

data class DetailEvaluationPaper(
    val id: Long,
    val processOnOpeningId: Long,
    val order: Int,
    val useComment: Boolean,
    val name: String,
    val notice: String?,
    val scoreType: EvaluationScoreType?,
    val scoreShowType: EvaluationScoreShowType,
    val evaluationOptions: List<EvaluationOption>,
    val evaluationWeight: Int?,
    val scoreCriteria: Int,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
