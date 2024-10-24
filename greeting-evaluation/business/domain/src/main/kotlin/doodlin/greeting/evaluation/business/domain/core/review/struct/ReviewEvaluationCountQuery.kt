package doodlin.greeting.evaluation.business.domain.core.review.struct

data class ReviewEvaluationCountQuery(
    val isCompleted: Boolean = false,
    val pendingCount: Long = 0,
    val acceptedCount: Long = 0,
    val heldCount: Long = 0,
    val rejectedCount: Long = 0,
)
