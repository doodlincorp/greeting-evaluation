package doodlin.greeting.concept.business.application.port.input

import doodlin.greeting.concept.business.application.AbstractFixtureMonkeyBasedTest
import doodlin.greeting.concept.business.application.port.output.ConceptManagementOutputPort
import doodlin.greeting.concept.business.application.usecase.ConceptManagementUsecase
import doodlin.greeting.concept.business.domain.ConceptManagementService
import doodlin.greeting.concept.business.domain.model.Concept
import doodlin.greeting.concept.business.domain.model.constants.ConceptTypes
import doodlin.greeting.concept.business.domain.model.struct.Idea
import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class ConceptManagementInputPortTest : AbstractFixtureMonkeyBasedTest() {

    @Test
    fun `Name과 Title을 입력했을 때 입력한 Name과 Title이 포함된 Concept를 생성한다`() {

        // mocking
        val conceptManagementOutputPortFixture: ConceptManagementOutputPort = mock()

        val name: String = "name"
        val title: String = "title"

        val concept: Concept =
            ConceptManagementService.create(name = name, title = title)

        // when
        `when`(conceptManagementOutputPortFixture.save(concept)).thenReturn(concept)

        val conceptManagementInputPortFixture: ConceptManagementUsecase =
            ConceptManagementInputPort(
                conceptManagementOutputPort = conceptManagementOutputPortFixture
            )

        val result: Concept = conceptManagementInputPortFixture.createConcept(
            name = name,
            title = title
        )

        // verifying
        verify(conceptManagementOutputPortFixture).save(concept)

        // assertion
        assert(result.name == name)
        assert(result.idea.title == title)
    }

    @Test
    fun `Name과 Title을 입력했을 때 입력한 Name과 Title이 포함된 Concept를 생성한다 - Domain Service Mocking`() {

        // mocking
        val name: String = "name"
        val title: String = "title"

        val concept = Concept(
            id = 0,
            idea = Idea(
                title = title
            ), type = ConceptTypes.NORMAL,
            name = name
        )

        val conceptManagementOutputPortFixture: ConceptManagementOutputPort = mock()

        // mocking & when
        // // 해당 Application 에서 호출하는 Domain Service의 로직이 복잡하거나, 고려해야 할 사항이 많아 작성이 어려운 경우 아래와 같이 mockkObject (mockStatic)을 생성해 처리한다.
        mockkObject(ConceptManagementService.Companion)
        every { ConceptManagementService.create(name = name, title = title) } returns concept

        `when`(conceptManagementOutputPortFixture.save(concept)).thenReturn(concept)

        val conceptManagementInputPortFixture: ConceptManagementUsecase =
            ConceptManagementInputPort(
                conceptManagementOutputPort = conceptManagementOutputPortFixture
            )

        val result: Concept = conceptManagementInputPortFixture.createConcept(
            name = name,
            title = title
        )

        // verifying
        verify(conceptManagementOutputPortFixture).save(concept)

        // assertion
        assert(result.name == name)
        assert(result.idea.title == title)
    }
}
