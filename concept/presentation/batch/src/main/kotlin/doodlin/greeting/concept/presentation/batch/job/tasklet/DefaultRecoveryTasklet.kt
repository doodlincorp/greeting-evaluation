package doodlin.greeting.concept.presentation.batch.job.tasklet

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

/**
 * Batch Job이 실패했을 때 수행되는 기본 Tasklet 이다.
 */
internal class DefaultRecoveryTasklet : Tasklet {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        with(chunkContext.stepContext.stepExecution.jobExecution) {
            this.stepExecutions
                .firstOrNull()?.failureExceptions
                ?.firstOrNull()?.let {
                    logger.error(it.stackTraceToString())
                }
        }
        contribution.exitStatus = ExitStatus.FAILED
        return RepeatStatus.FINISHED
    }
}
