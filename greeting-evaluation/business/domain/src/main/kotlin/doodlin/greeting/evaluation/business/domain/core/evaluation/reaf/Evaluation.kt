package doodlin.greeting.evaluation.business.domain.core.evaluation.reaf

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationOption
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreCalculationType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreShowType
import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreType
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
) {
    fun checkEvaluationAuthority(hasPrivateEvaluationInquiryAuthority: Boolean) {
        // 평가 디브리핑 체크
        if (!hasPrivateEvaluationInquiryAuthority && privateEvaluation != this.privateEvaluation) {
            throw ForbiddenException(ApplicantErrors.GENERAL_CANNOT_HAVE_EVALUATION_PRIVATE_VALUE.message)
        }

        // 평가척도가 커스텀인 경우, 옵션에 존재하는 값을 선택했는지 확인
        if (EvaluationScoreType.getNeedEvaluationOptionSizeOrZero(this.totalScoreType) > 0) {
            this.evaluationOptions.find {
                it.score == score && it.scoreText == scoreText
            } ?: throw BadRequestException(EvaluationException.CAN_NOT_SELECT_EVALUATION_OPTION.message)
        }
    }
}
