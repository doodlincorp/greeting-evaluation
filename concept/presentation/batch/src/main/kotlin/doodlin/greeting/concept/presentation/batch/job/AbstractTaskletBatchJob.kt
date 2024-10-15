package doodlin.greeting.concept.presentation.batch.job

import doodlin.greeting.concept.presentation.batch.constants.BatchProcessNameFormats
import doodlin.greeting.concept.presentation.batch.constants.ExitStatusCodes
import doodlin.greeting.concept.presentation.batch.job.tasklet.DefaultRecoveryTasklet
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.transaction.PlatformTransactionManager

/**
 * 해당 클래스는 Task 기반의 Single Step으로 구성된 추상 Batch Job이다.
 */
internal abstract class AbstractTaskletBatchJob(
    name: String,
    private val recoveryTasklet: Tasklet = DefaultRecoveryTasklet(),
) : BatchJob(name = name) {

    protected val jobParameters: MutableMap<String, String> = mutableMapOf()

    @Bean
    @StepScope
    fun processTasklet(
        @Value("#{jobParameters}") jobParameters: Map<String, String>,
    ): Tasklet {
        this.jobParameters.clear()
        this.jobParameters.putAll(from = jobParameters)
        return Tasklet { contribution, chunkContext -> this.process(contribution, chunkContext) }
    }

    @Bean
    fun step(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        processTasklet: Tasklet,
    ): Step {
        return StepBuilder(
            BatchProcessNameFormats.STEP_NAME_FORMAT.format(jobNameWithTimestamp),
            jobRepository
        ).tasklet(processTasklet, transactionManager)
            .allowStartIfComplete(true) // 해당 스텝의 성공 이력이 존재하더라도 재실행
            .build()
    }

    @Bean
    fun recoveryStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
    ): Step {
        return StepBuilder(
            BatchProcessNameFormats.RECOVERY_STEP_NAME_FORMAT.format(jobNameWithTimestamp),
            jobRepository
        ).tasklet(recoveryTasklet, transactionManager)
            .transactionManager(transactionManager)
            .allowStartIfComplete(true)
            .build()
    }

    @Bean
    fun job(
        jobRepository: JobRepository,
        step: Step,
        recoveryStep: Step,
    ): Job {
        return JobBuilder(
            BatchProcessNameFormats.JOB_NAME_FORMAT.format(jobNameWithTimestamp),
            jobRepository
        ).preventRestart() // 실패한 JOB에 대해서 재실행 방지
            .incrementer(RunIdIncrementer())
            .start(step)
            .on(ExitStatus.FAILED.exitCode)
            .to(recoveryStep)
            .from(step)
            .on(ExitStatusCodes.ASTERISK)
            .end()
            .end()
            .build()
    }
}
