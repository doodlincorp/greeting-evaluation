package doodlin.greeting.concept.adaptor.mysql.entity

import doodlin.greeting.concept.business.domain.model.constants.ConceptTypes
import doodlin.greeting.concept.business.domain.model.struct.Idea
import io.hypersistence.utils.hibernate.type.json.JsonStringType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "Concept")
internal class ConceptEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: ConceptTypes,

    @Type(JsonStringType::class)
    @Column(name = "idea", columnDefinition = "json")
    val idea: Idea,

    @Column(name = "name")
    val name: String,
)
