package doodlin.greeting.concept.adaptor.kafka

import doodlin.messaging.kafka.producer.LowLevelProducerFactory
import org.apache.kafka.clients.producer.Producer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@Configuration
@EnableKafka
@ComponentScan(basePackages = ["doodlin.messaging.kafka"])
internal class KafkaConfiguration(
    private val lowLevelProducerFactory: LowLevelProducerFactory,
) {

    @Value("\${kafka.default.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    internal fun lowLevelProducer(): Producer<String, String> =
        lowLevelProducerFactory.createProducer(
            bootstrapServers = bootstrapServers,
            ack = ACK_ALL,
            retryCount = RETRY_COUNT,
            clientId = CLIENT_ID
        )

    companion object {
        const val CLIENT_ID = "ats"
        const val ACK_ALL = "all"
        const val RETRY_COUNT = 3
    }
}
