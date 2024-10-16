package doodlin.greeting.concept.adaptor.kafka

import org.apache.kafka.clients.producer.Producer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class KafkaPortConfiguration(
    private val lowLevelProducer: Producer<String, String>,
) {
    @Bean
    fun conceptEventProducer(): ConceptEventOutputPort<String> =
        DefaultKafkaEventMessageProducer(
            topic = KafkaTopics.EVENT_TEST,
            producer = lowLevelProducer
        )
}
