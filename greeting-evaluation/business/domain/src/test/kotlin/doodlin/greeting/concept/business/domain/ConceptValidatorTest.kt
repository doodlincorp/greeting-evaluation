package doodlin.greeting.concept.business.domain

import com.navercorp.fixturemonkey.kotlin.setExp
import doodlin.greeting.concept.business.domain.model.Concept
import doodlin.greeting.concept.business.domain.model.constants.ConceptTypes
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ConceptValidatorTest : AbstractFixtureMonkeyBasedTest() {
    @Test
    fun `유효성 검증 과정에서 Concept의 type이 ConceptTypes_EXPERT일 때 IllegalStateException을 반환한다`() {
        val expertConcept: Concept = DEFAULT_FIXTURE_MONKEY.giveMeBuilder(Concept::class.java)
            .setExp(Concept::type, ConceptTypes.EXPERT)
            .build().sample()

        assertThrows<IllegalStateException> {
            ConceptValidator.validate(expertConcept)
        }
    }

    @Test
    fun `유효성 검증 과정에서 Concept의 type이 ConceptTypes_EXPERT이 아닐 때 IllegalStateException을 반환하지 않는다`() {
        val notExpertConcept: Concept = DEFAULT_FIXTURE_MONKEY.giveMeBuilder(Concept::class.java)
            .setPostCondition("type", ConceptTypes::class.java) { it.equals(ConceptTypes.EXPERT).not() }
            .build().sample()

        assertDoesNotThrow {
            ConceptValidator.validate(notExpertConcept)
        }
    }
}
