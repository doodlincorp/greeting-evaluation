package doodlin.greeting.evaluation.business.domain.evaluation_paper_template.sub

import doodlin.greeting.evaluation.business.domain.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreType

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
    val scoreCriteria: Int
) {
}