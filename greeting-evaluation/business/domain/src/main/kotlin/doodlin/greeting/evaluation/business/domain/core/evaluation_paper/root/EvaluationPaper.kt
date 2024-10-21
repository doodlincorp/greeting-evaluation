package doodlin.greeting.evaluation.business.domain.core.evaluation_paper.root

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreType
import doodlin.greeting.evaluation.business.domain.core.evaluation_paper.reaf.*
import java.time.ZonedDateTime

data class EvaluationPaper(
    val id: Long,
    val processOnOpeningId: Long,
    val makeEvaluationPrivate: Boolean,
    val evaluationNotice: String?,
    val totalScoreType: EvaluationScoreType,
    val scoreShowType: EvaluationScoreShowType,
    val totalScoreShowType: EvaluationScoreShowType,
    val evaluationName: String?,
    val evaluationOptions: List<EvaluationOption>,
    val scoreCriteria: Int,
    val evaluationRemindSetting: EvaluationRemindSetting?,
    val detailEvaluationPapers: Set<DetailEvaluationPaper>,
    val evaluationCalculationSetting: EvaluationCalculationSetting?,
    val evaluationGroups: Set<EvaluationGroup>,
    val evaluators: Set<Evaluator>,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}


data class LegacyEvaluationPaper(
    val id: Long, // 채용 단계 id
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
