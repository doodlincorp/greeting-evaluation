package doodlin.greeting.concept.adaptor.mysql.repository

import doodlin.greeting.concept.adaptor.mysql.entity.mapper.toEntity
import doodlin.greeting.concept.adaptor.mysql.entity.mapper.toModel
import doodlin.greeting.concept.adaptor.mysql.repository.jpa.ConceptJpaRepository
import doodlin.greeting.concept.business.application.port.output.ConceptManagementOutputPort
import doodlin.greeting.concept.business.domain.model.Concept
import org.springframework.stereotype.Repository

@Repository
internal class ConceptRepository(
    private val conceptJpaRepository: ConceptJpaRepository,
) : ConceptManagementOutputPort {
    override fun save(concept: Concept): Concept {
        return conceptJpaRepository.save(concept.toEntity()).toModel()
    }
}
