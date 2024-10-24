package doodlin.greeting.evaluation.business.domain.core.evaluation_test

data class PersonalityTester(
    val id: Long,
    val applicantId: Long,
    val requestPersonalityTestId: Long,
    val personalityTestResults: Set<PersonalityTestResult>
)
