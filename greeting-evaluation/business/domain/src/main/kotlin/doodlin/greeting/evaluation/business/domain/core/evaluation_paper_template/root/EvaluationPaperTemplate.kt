package doodlin.greeting.evaluation.business.domain.core.evaluation_paper_template.root

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreType
import doodlin.greeting.evaluation.business.domain.core.evaluation_paper_template.reaf.DetailEvaluationPaperTemplate
import doodlin.greeting.evaluation.business.domain.core.evaluation_paper_template.reaf.EvaluationPaperCalculationTemplate
import java.time.ZonedDateTime

data class EvaluationPaperTemplate(
    val id: Long,
    val workspaceId: Long,
    val name: String,
    val totalScoreType: EvaluationScoreType,
    val updateUserId: Long,
    val notice: String?,
    val scoreShowType: EvaluationScoreShowType,
    val totalScoreShowType: EvaluationScoreShowType,
    val evaluationOptions: List<EvaluationOption>,
    val scoreCriteria: Long,
    val detailEvaluationPaperTemplates: Set<DetailEvaluationPaperTemplate>,
    val evaluationCalculationPaperTemplate: EvaluationPaperCalculationTemplate?,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
)
