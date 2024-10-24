package doodlin.greeting.greetingtrmserver.review.domain.model

import doodlin.greeting.greetingtrmserver.errorcode.ReviewErrorCode
import doodlin.greeting.greetingtrmserver.exception.BadRequestException
import java.time.ZonedDateTime
import java.util.UUID

data class ReviewEvaluationDomain(
    val id: Long = 0,

    val reviewId: UUID,

    val reviewerId: Long,

    val candidateId: Long,

    val evaluation: EvaluationType,

    val evaluatedAt: ZonedDateTime? = null,

    val createdAt: ZonedDateTime = ZonedDateTime.now(),

    val updatedAt: ZonedDateTime = ZonedDateTime.now(),

    val deletedAt: ZonedDateTime? = null,
) {
    fun evaluate(
        evaluationType: EvaluationType,
        updateEvaluatedAt: ZonedDateTime,
    ): ReviewEvaluationDomain {
          if (evaluationType == EvaluationType.REVIEW_PENDING) {
              throw BadRequestException(code = ReviewErrorCode.INVALID_EVALUATE_TYPE)
          }

        return copy(
              evaluation = evaluationType,
              evaluatedAt = updateEvaluatedAt,
          )
    }
}

enum class EvaluationType {
    REVIEW_PENDING,
    REVIEW_ACCEPTED,
    REVIEW_REJECTED,
    REVIEW_HELD,
}

fun List<ReviewEvaluationDomain>.isReviewCompleted(): Boolean {
    return this.all { it.evaluation != EvaluationType.REVIEW_PENDING }
}

/**
 * 내용 : 리뷰에 대한 평가가 존재하면 리뷰어로 판단
 */
fun List<ReviewEvaluationDomain>.validReviewer(): Boolean {
    return this.isNotEmpty()
}
