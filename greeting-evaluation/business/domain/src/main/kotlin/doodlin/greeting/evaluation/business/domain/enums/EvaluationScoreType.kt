package doodlin.greeting.evaluation.business.domain.enums

enum class EvaluationScoreType(val scoreType: Int) {
    STEP3(EvaluationScoreTypeConstant.STEP3),
    STEP5(EvaluationScoreTypeConstant.STEP5),
    STEP100(EvaluationScoreTypeConstant.STEP100),
    CUSTOM_STEP2(EvaluationScoreTypeConstant.CUSTOM_STEP2),
    CUSTOM_STEP3(EvaluationScoreTypeConstant.CUSTOM_STEP3),
    CUSTOM_STEP4(EvaluationScoreTypeConstant.CUSTOM_STEP4),
    CUSTOM_STEP5(EvaluationScoreTypeConstant.CUSTOM_STEP5),
    CUSTOM_STEP6(EvaluationScoreTypeConstant.CUSTOM_STEP6),
    CUSTOM_STEP7(EvaluationScoreTypeConstant.CUSTOM_STEP7),
    CUSTOM_STEP8(EvaluationScoreTypeConstant.CUSTOM_STEP8),
    CUSTOM_STEP9(EvaluationScoreTypeConstant.CUSTOM_STEP9),
    CUSTOM_STEP10(EvaluationScoreTypeConstant.CUSTOM_STEP10);

    fun isNotDefaultType(): Boolean {
        return this != STEP5
    }

    object EvaluationScoreTypeConstant {
        const val STEP3 = 3
        const val STEP5 = 5
        const val STEP7 = 7
        const val STEP100 = 100
        const val CUSTOM_STEP2 = 1002
        const val CUSTOM_STEP3 = 1003
        const val CUSTOM_STEP4 = 1004
        const val CUSTOM_STEP5 = 1005
        const val CUSTOM_STEP6 = 1006
        const val CUSTOM_STEP7 = 1007
        const val CUSTOM_STEP8 = 1008
        const val CUSTOM_STEP9 = 1009
        const val CUSTOM_STEP10 = 1010
    }

    companion object {
        private const val CUSTOM_EVALUATION_OPTION_START_DIGIT = 1000
        private const val CUSTOM_EVALUATION_OPTION_MINIMUM_DIGIT = 0

        fun getNeedEvaluationOptionSizeOrZero(scoreType: EvaluationScoreType): Int {
            // 평가 총점 타입: 커스텀일 경우 1002 이상, 커스텀이 아닐 경우 3, 5, 7, 100
            val scoreTypeNumber = scoreType.scoreType

            // 커스텀일 경우 (2 ~ 10).coerceAtLeast(0) 중 호출된 값이 더 크므로 2 ~ 10 반환
            // 커스텀이 아닐 경우 (-997 ~ 900).coerceAtLeast(0) 중 호출된 값이 더 작으므로 0 반환
            return (scoreTypeNumber - CUSTOM_EVALUATION_OPTION_START_DIGIT)
                .coerceAtLeast(CUSTOM_EVALUATION_OPTION_MINIMUM_DIGIT)
        }
    }
}
