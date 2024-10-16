package doodlin.greeting.concept.business.application

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.introspector.ArbitraryIntrospectorResult
import com.navercorp.fixturemonkey.api.jqwik.ArbitraryUtils
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import net.jqwik.api.Arbitraries

abstract class AbstractFixtureMonkeyBasedTest {

    /**
     * 클래스 내 테스트에서 사용할 FixtureMonkey를 선언한다.
     * String Type은 극단적인 경우를 고려하기 때문에 Unreadable 데이터로 제공된다.
     * - JakartaValidationPlugin : Jakarta Validation이 적용된 경우, 해당 Validation을 참조해 데이터를 생성한다.
     * - KotlinPlugin : Koltin에서 제공하는 객체나 자료형에 대한 호환성을 제공한다.
     * - defaultNotNull(false) : 기본적으로 nullable한 필드에 null / notnull value를 모두 제공한다.
     */
    protected val DEFAULT_FIXTURE_MONKEY: FixtureMonkey = FixtureMonkey.builder()
        .plugin(JakartaValidationPlugin())
        .plugin(KotlinPlugin())
        .defaultNotNull(false)
        .build()

    /**
     * String 필드에 Alphabet만 삽입하는 FixtureMonkey를 선언한다.
     * 단, 특정 필드만 사용하는 것이 불가능하다.
     * 특정 필드에 대해서만 설정하고 싶을 때는 FixtureMonkey.build의 결과물인 Arbitrary 수준에서 해결한다.
     */
    @Suppress("UnusedPrivateMember")
    private val ALPHA_STRING_FIXTURE_MONKEY: FixtureMonkey = FixtureMonkey.builder()
        .plugin(JakartaValidationPlugin())
        .plugin(KotlinPlugin())
        .pushExactTypeArbitraryIntrospector(String::class.java) {
            ArbitraryIntrospectorResult(
                ArbitraryUtils.toCombinableArbitrary(Arbitraries.strings().alpha())
            )
        }.build()
}
