package doodlin.greeting.evaluation.business.domain.core.review.struct

data class ReviewRequesterQuery(
    val requesterId: Long = 0,

    val requesterEmail: String = "정보없음",

    val requesterName: String = "정보없음",

    val requesterProfileImage: String? = null,
)
