package doodlin.greeting.concept.presentation.consumer

import doodlin.common.logging.DLogger
import doodlin.greeting.concept.presentation.consumer.dto.TestDto
import doodlin.messaging.kafka.consumer.KafkaMessageProcessor
import org.springframework.stereotype.Component

@Component
class TestConsumer : KafkaMessageProcessor<TestDto>() {
    override val topic: String
        get() = "exception.test.v1"

    /**
     * autoStart 는 application.yml 에 정의된 값을 default 로 사용합니다.
     * null 로 설정하면 application.yml 에 정의된 값을 사용하고,
     * true/false 로 설정하면 해당 값으로 override 합니다.
     */
    override val autoStart: Boolean?
        get() = true

    /**
     * groupId는 application.yml 에 정의된 값을 default 로 사용합니다.
     * 필요에 따라 override 하여 사용할 수 있지만,
     * 같은 이름 중복을 피하기 위해 applicant-consumer 와 같은 서비스 이름이 포함되지 않은 단순한 이름은 지양합니다.
     */
//    override val groupId: String = ""

    override fun process(data: TestDto) {
        log.info("data ################### $data")
    }

    companion object : DLogger()
}
