package doodlin.greeting.evaluation.business.domain.core.evaluation.factory

import doodlin.greeting.evaluation.business.domain.core.enums.EvaluationScoreCalculationType

class EvaluationCalculatorFactory : CalculatorType {
    override var type: EvaluationScoreCalculationType = EvaluationScoreCalculationType.MANUAL

    override fun calculate(): Int {
        return
    }
}

interface CalculatorType {
    var type: EvaluationScoreCalculationType

    fun calculate(): Int
}
