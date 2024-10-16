package doodlin.greeting.concept.presentation.batch.job

import doodlin.greeting.concept.presentation.batch.constants.PropertyNames
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.util.*

/**
 * yaml과 같은 설정 파일 또는 실행 옵션 중 name (spring.batch.job.names) property에 <br>
 * havingValue 에 해당하는 값이 존재할 경우에 해당 빈을 생성한다.
 */
@Component
@ConditionalOnProperty(
    name = [PropertyNames.SPRING_BATCH_JOB_NAMES],
    havingValue = JobNames.SAMPLE_JOB
)
internal class SampleTaskletBatchJob : AbstractTaskletBatchJob(
    name = JobNames.SAMPLE_JOB
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    /**
     *  Task 기반의 SingleStep Batch 이기 때문에, 실행 함수 하나만 존재한다.
     *  Batch 실행 시, 해당 함수 내에 선언한 기능이 동작한다.
     *
     *  성공 시, RepeatStatus.FINISHED 리턴한다.
     *  성공 실패, 또는 예외가 발생했을 경우에는 AbstractTaskletBatchJob의 recoveryStep에 선언된 프로세스가 수행된다.
     *
     *  참고로 Job의 구성에 따라 RepeatStatus 를 핸들링할 수 있으며, 상태에 따라 수행되는 플로우를 구성할 수도 있다.
     */
    override fun process(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        logger.info(TimeZone.getDefault().displayName)
        logger.info(jobParameters.toString())
        return RepeatStatus.FINISHED
    }
}
