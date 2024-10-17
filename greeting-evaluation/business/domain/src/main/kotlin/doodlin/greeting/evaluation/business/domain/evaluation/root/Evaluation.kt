package doodlin.greeting.evaluation.business.domain.evaluation.root

import doodlin.greeting.evaluation.business.domain.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreType
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreCalculationType
import java.time.ZonedDateTime

data class Evaluation(
    val id: Int,
    val userId: Int?,
    val score: Int?,
    val scoreText: String?,
    val comment: String?,
    val notice: String?,
    val processOnOpeningId: Int,
    val applicantId: Int,
    val isLock: Boolean,
    val privateEvaluation: Boolean,
    val scoreShowType: EvaluationScoreShowType,
    val evaluationOptions: List<EvaluationOption>,
    val totalScoreType: EvaluationScoreType,
    val scoreCalculationType: EvaluationScoreCalculationType,
    val scoreCriteria: Int,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
