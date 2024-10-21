package doodlin.greeting.evaluation.business.domain.core.evaluation_paper.reaf

data class EvaluationGroup(
    val id: Long,
    val evaluationPaperId: Long,
    val applicantId: Long
) {
}