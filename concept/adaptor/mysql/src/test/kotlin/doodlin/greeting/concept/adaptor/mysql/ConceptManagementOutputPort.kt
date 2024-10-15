package doodlin.greeting.concept.adaptor.mysql

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.introspector.ArbitraryIntrospectorResult
import com.navercorp.fixturemonkey.api.jqwik.ArbitraryUtils
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.setExp
import doodlin.greeting.concept.business.application.port.output.ConceptManagementOutputPort
import doodlin.greeting.concept.business.domain.model.Concept
import doodlin.greeting.concept.business.domain.model.struct.Idea
import net.datafaker.Faker
import net.jqwik.api.Arbitraries
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("ConceptManagementOutputPort")
class ConceptManagementOutputPort : AbstractMySQLTestContainerBasedTest() {

    @Autowired
    lateinit var conceptOutport: ConceptManagementOutputPort

    private val log = LoggerFactory.getLogger(javaClass)

    @RepeatedTest(5)
    fun `임의의 Concept 객체를  Fixture Monkey 수준에서 자동으로 테스트 객체를 생성하고 이를 데이터베이스에 저장한다`() {
        // given
        val nonPersisted: Concept = DEFAULT_FIXTURE_MONKEY.giveMeOne(Concept::class.java)

        // when
        val persisted: Concept = conceptOutport.save(nonPersisted)

        // then
        then(persisted.id).isGreaterThan(0)
    }

    /**
     * Arbitrary 수준에서 테스트 데이터를 생성하고 이를 처리한다.
     * - 기본적으로 FixtureMonkey의 설정을 따르며, setExp와 같은 함수를 통해 별도로 지정할 수도 있다.
     * - 단, setExp와 같이 직접 데이터를 추가해준 경우 Bean Validation Annotation에 통과하지 못 하는 데이터가 추가될 수도 있으니 주의해야 한다.
     */
    @RepeatedTest(5)
    fun `영속화되지 않은 Concept 객체를 Arbitrary 수준의 테스트 객체를 생성하고 이를 데이터베이스에 저장한다`() {
        // given
        val nonPersisted: Concept =
            ALPHA_STRING_FIXTURE_MONKEY.giveMeBuilder(Concept::class.java)
                .setExp(Concept::id, 0)
                .build()
                .sample()

        // when
        val persisted: Concept = conceptOutport.save(nonPersisted)

        // then
        then(persisted.id).isGreaterThan(0)

        // print
        log.info(persisted.toString())
    }

    @RepeatedTest(5)
    fun `영속화되지 않은 Concept 객체를 Arbitrary 수준의 테스트 객체를 생성하고 이를 데이터베이스에 저장한다 (내부 POJO 필드 직접 생성)`() {

        // given
        val nonPersisted: Concept =
            ALPHA_STRING_FIXTURE_MONKEY.giveMeBuilder(Concept::class.java)
                .setExp(Concept::id, 0)
                .setExp(Concept::name, Faker().name().name())
                .setExp(
                    Concept::idea, ALPHA_STRING_FIXTURE_MONKEY.giveMeBuilder(Idea::class.java)
                        .setExp(Idea::title, Faker().book().title()).build()
                )
                .build()
                .sample()

        // when
        val persisted: Concept = conceptOutport.save(nonPersisted)

        // then
        then(persisted.id).isGreaterThan(0)

        // print
        log.info(persisted.toString())
    }

    companion object {
        /**
         * 클래스 내 테스트에서 사용할 FixtureMonkey를 선언한다.
         * String Type은 극단적인 경우를 고려하기 때문에 Unreadable 데이터로 제공된다.
         * - JakartaValidationPlugin : Jakarta Validation이 적용된 경우, 해당 Validation을 참조해 데이터를 생성한다.
         * - KotlinPlugin : Koltin에서 제공하는 객체나 자료형에 대한 호환성을 제공한다.
         */
        private val DEFAULT_FIXTURE_MONKEY: FixtureMonkey = FixtureMonkey.builder()
            .plugin(JakartaValidationPlugin())
            .plugin(KotlinPlugin())
            .build()

        /**
         * String 필드에 Alphabet만 삽입하는 FixtureMonkey를 선언한다.
         * 단, 특정 필드만 사용하는 것이 불가능하다.
         * 특정 필드에 대해서만 설정하고 싶을 때는 FixtureMonkey.build의 결과물인 Arbitrary 수준에서 해결한다.
         */
        private val ALPHA_STRING_FIXTURE_MONKEY: FixtureMonkey = FixtureMonkey.builder()
            .plugin(JakartaValidationPlugin())
            .plugin(KotlinPlugin())
            .pushExactTypeArbitraryIntrospector(String::class.java) {
                ArbitraryIntrospectorResult(
                    ArbitraryUtils.toCombinableArbitrary(Arbitraries.strings().alpha())
                )
            }.build()
    }
}
