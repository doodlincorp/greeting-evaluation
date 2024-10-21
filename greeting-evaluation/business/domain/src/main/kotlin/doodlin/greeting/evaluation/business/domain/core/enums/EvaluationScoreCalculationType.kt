package doodlin.greeting.evaluation.business.domain.core.enums

import doodlin.greeting.evaluation.business.domain.errors.EvaluationException

enum class EvaluationScoreCalculationType : CheckEvaluationCalculation {
    MANUAL {
        override fun checkWeightSize(weightSize: Int) {
            // 가중치가 존재해선 안됨
            if (weightSize > 0) {
                throw BadRequestException(EvaluationException.EVALUATION_WEIGHT_ONLY_WEIGHT_SCORE.message)
            }
        }

        override fun checkDetailEvaluationSize(detailEvaluationSize: Int) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        override fun checkWeightScore(notValidWeightScore: Boolean) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        override fun checkWeightSumAndSize(weightSum: Int, weightSize: Int) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }
    },

    @Deprecated("2차 스프린트에 사용 예정")
    TOTAL_SCORE {
        override fun checkWeightSize(weightSize: Int) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        override fun checkDetailEvaluationSize(detailEvaluationSize: Int) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        override fun checkWeightScore(notValidWeightScore: Boolean) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        override fun checkWeightSumAndSize(weightSum: Int, weightSize: Int) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }
    },
    AVERAGE_SCORE {
        override fun checkWeightSize(weightSize: Int) {
            // 가중치가 존재해선 안됨
            if (weightSize > 0) {
                throw BadRequestException(EvaluationException.EVALUATION_WEIGHT_ONLY_WEIGHT_SCORE.message)
            }
        }

        override fun checkDetailEvaluationSize(detailEvaluationSize: Int) {
            // 평가지가 최소 1개 이상이어야 함
            if (detailEvaluationSize == 0) {
                throw BadRequestException(EvaluationException.MUST_HAVE_DETAIL_EVALUATION_EXCEPT_MANUAL.message)
            }
        }

        override fun checkWeightScore(notValidWeightScore: Boolean) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        override fun checkWeightSumAndSize(weightSum: Int, weightSize: Int) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }
    },
    WEIGHT_SCORE {
        override fun checkWeightSize(weightSize: Int) {
            throw UnsupportedOperationException("지원하지 않는 기능입니다")
        }

        override fun checkDetailEvaluationSize(detailEvaluationSize: Int) {
            // 평가지가 최소 1개 이상이어야 함
            if (detailEvaluationSize == 0) {
                throw BadRequestException(EvaluationException.MUST_HAVE_DETAIL_EVALUATION_EXCEPT_MANUAL.message)
            }
        }

        override fun checkWeightScore(notValidWeightScore: Boolean) {
            // 가중치와 점수 둘다 비어있거나 없어야 함
            if (notValidWeightScore) {
                throw BadRequestException(EvaluationException.NOT_VALID_SCORE_AND_WEIGHT.message)
            }
        }

        override fun checkWeightSumAndSize(weightSum: Int, weightSize: Int) {
            // 가중치의 총합은 100%여야 하며, 가중치가 비어있으면 안됨
            if (
                weightSum != EvaluationPolicy.EVALUATION_WEIGHT_SUM ||
                weightSize == 0
            ) {
                throw BadRequestException(EvaluationException.INVALID_EVALUATION_WEIGHT.message)
            }
        }
    }
}

interface CheckEvaluationCalculation {
    fun checkWeightSize(weightSize: Int)
    fun checkDetailEvaluationSize(detailEvaluationSize: Int)
    fun checkWeightScore(notValidWeightScore: Boolean)
    fun checkWeightSumAndSize(weightSum: Int, weightSize: Int)
}