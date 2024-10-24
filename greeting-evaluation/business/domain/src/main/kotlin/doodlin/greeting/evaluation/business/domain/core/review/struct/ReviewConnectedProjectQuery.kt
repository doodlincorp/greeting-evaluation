package doodlin.greeting.evaluation.business.domain.core.review.struct

data class ReviewConnectedProjectQuery(
    val projectId: Long,
    val projectName: String?,
    val statusId: Long? = null,
    val statusName: String? = null,
)
