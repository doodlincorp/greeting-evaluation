package doodlin.greeting.evaluation.business.domain.core.evaluation_test

data class RequestPersonalityTest(
    val id: Long,
    val personalityTestIntegrationWorkspaceId: Long,
    val personalityTestPaperId: Long,
    val openingId: Long
)
