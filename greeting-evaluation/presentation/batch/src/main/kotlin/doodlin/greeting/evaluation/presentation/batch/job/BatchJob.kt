package doodlin.greeting.concept.presentation.batch.job

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus

internal abstract class BatchJob(
    name: String,
) {
    val jobNameWithTimestamp: String =
        name + "_" + System.currentTimeMillis().toString()

    @Throws(Exception::class)
    abstract fun process(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus
}
