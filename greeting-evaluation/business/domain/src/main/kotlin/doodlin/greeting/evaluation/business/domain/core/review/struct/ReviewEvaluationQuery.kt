package doodlin.greeting.evaluation.business.domain.core.review.struct

import doodlin.greeting.greetingtrmserver.review.domain.model.EvaluationType
import java.time.ZonedDateTime
import java.util.*

data class ReviewEvaluationQuery(
    val id: Long,
    val reviewId: UUID,
    val candidateId: Long,
    val requesterId: Long,
    val evaluation: EvaluationType,
    val createAt: ZonedDateTime,
)
