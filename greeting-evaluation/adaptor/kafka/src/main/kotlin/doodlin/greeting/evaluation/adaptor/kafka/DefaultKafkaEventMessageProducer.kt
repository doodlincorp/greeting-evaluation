package doodlin.greeting.concept.adaptor.kafka

import doodlin.messaging.kafka.producer.KafkaProducerCallback
import doodlin.messaging.kafka.producer.KafkaProducerSupport
import org.apache.kafka.clients.producer.Producer
import java.time.Duration

internal class DefaultKafkaEventMessageProducer<T>(
    topic: String,
    producer: Producer<String, String>,
    callback: KafkaProducerCallback<T>? = null,
    timeout: Duration = Duration.ofSeconds(KafkaPolicy.DEFAULT_TIMEOUT_SEC),
) : ConceptEventOutputPort<T>, KafkaProducerSupport<T>(
    topic = topic,
    producer = producer,
    callback = callback,
    timeout = timeout
) {
    override fun publish(event: T) {
        super.sendAsync(event)
    }
}
