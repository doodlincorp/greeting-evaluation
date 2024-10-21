package doodlin.greeting.evaluation.business.domain.core.evaluation_paper_template.reaf

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreType
import java.time.ZonedDateTime

data class DetailEvaluationPaperTemplate(
    val id: Long,
    val evaluationTemplateId: Int,
    val order: Int,
    val name: String,
    val notice: String?,
    val scoreType: EvaluationScoreType?,
    val scoreShowType: EvaluationScoreShowType,
    val evaluationOptions: List<EvaluationOption>,
    val useComment: Boolean,
    val evaluationWeight: Int?,
    val scoreCriteria: Int,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}