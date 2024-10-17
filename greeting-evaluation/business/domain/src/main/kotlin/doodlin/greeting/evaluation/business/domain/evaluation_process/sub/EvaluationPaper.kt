package doodlin.greeting.evaluation.business.domain.evaluation_process.sub

import doodlin.greeting.evaluation.business.domain.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreType
import java.time.ZonedDateTime

data class EvaluationPaper(
    val id: Long,
    val openingId: Long,
    val makeEvaluationPrivate: Boolean,
    val evaluationNotice: String?,
    val totalScoreType: EvaluationScoreType,
    val scoreShowType: EvaluationScoreShowType,
    val totalScoreShowType: EvaluationScoreShowType,
    val evaluationName: String?,
    val evaluationOptions: List<EvaluationOption>,
    val scoreCriteria: Int,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
