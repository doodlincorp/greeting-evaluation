package doodlin.greeting.evaluation.business.domain.model.evaluation_process

import doodlin.greeting.evaluation.business.domain.model.evaluation_process.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.model.evaluation_process.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.model.evaluation_process.enums.EvaluationScoreType

data class EvaluationPaper(
    val id: Int = 0,
    val openingId: Int = 0,
    val makeEvaluationPrivate: Boolean,
    val evaluationNotice: String?,
    val totalScoreType: EvaluationScoreType,
    val scoreShowType: EvaluationScoreShowType,
    val totalScoreShowType: EvaluationScoreShowType,
    val evaluationName: String?,
    val evaluationOptions: List<EvaluationOption>,
    val scoreCriteria: Int
)
