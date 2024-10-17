package doodlin.greeting.evaluation.business.domain.evaluation_paper_template.root

import doodlin.greeting.evaluation.business.domain.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreType
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
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
}