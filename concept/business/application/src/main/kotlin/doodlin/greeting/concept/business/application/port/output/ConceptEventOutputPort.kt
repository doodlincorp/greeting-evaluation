package doodlin.greeting.concept.business.application.port.output

interface ConceptEventOutputPort<T> {
    fun publish(event: T)
}
