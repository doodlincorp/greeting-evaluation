package doodlin.greeting.evaluation.business.domain.evaluation_process.sub

import doodlin.greeting.evaluation.business.domain.enums.EvaluationScoreCalculationType
import java.time.ZonedDateTime

data class EvaluationCalculationSetting(
    // 상세 평가표 계산 id
    val id: Long,
    // 상세 평가표 계산 최상위 채용단계 id
    val processOnOpeningId: Long,
    // 상세 평가표 계산 방식
    val scoreCalculationType: EvaluationScoreCalculationType,
    // 상세 평가표 계산 생성일
    val createDate: ZonedDateTime,
    // 상세 평가표 계산 수정일
    val updateDate: ZonedDateTime,
    // 상세 평가표 계산 삭제일
    val deletedAt: ZonedDateTime?
) {
}