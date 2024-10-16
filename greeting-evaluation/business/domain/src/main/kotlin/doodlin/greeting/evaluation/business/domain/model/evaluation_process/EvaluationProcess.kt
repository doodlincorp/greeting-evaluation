package doodlin.greeting.evaluation.business.domain.model.evaluation_process

data class EvaluationProcess(
    val id: Long,
    val evaluationPaper: EvaluationPaper?,
    val evaluators: Set<Evaluator>
) {
}