package doodlin.greeting.evaluation.business.domain.core.evaluation_paper_template.reaf

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreCalculationType
import java.time.ZonedDateTime

data class EvaluationPaperCalculationTemplate(
    // 상세 평가 템플릿 계산 id
    val id: Long,
    // 상세 평가 계산 최상위 평가 템플릿 id
    val evaluationTemplateId: Int,
    // 상세 평가 템플릿 계산 방식
    val scoreCalculationType: EvaluationScoreCalculationType,
    // 상세 평가 템플릿 계산 생성일
    val createDate: ZonedDateTime,
    // 상세 평가 템플릿 계산 수정일
    val updateDate: ZonedDateTime,
    // 상세 평가 템플릿 계산 삭제일
    val deletedAt: ZonedDateTime?
) {
}