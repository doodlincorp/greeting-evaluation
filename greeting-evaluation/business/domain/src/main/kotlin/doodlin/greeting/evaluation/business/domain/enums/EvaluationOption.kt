package doodlin.greeting.evaluation.business.domain.enums

data class EvaluationOption(
    val score: Int,
    val scoreText: String,
    val order: Int,
) {
    init {
        require(score in 0..100) { "0 ~ 100 사이의 점수만 입력이 가능합니다." }
        require(scoreText.isNotBlank()) { "원하는 옵션명을 입력해주세요." }
        require(scoreText.length <= 300) { "옵션명은 최대 300 자까지 입력 가능합니다." }
    }
}
