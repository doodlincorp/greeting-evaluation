package doodlin.greeting.concept.adaptor.mysql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Adaptor는 참조할 수 있는 어플리케이션이 없기 때문에 별도로 TestApplication 클래스를 선언한다.
 */
@SpringBootApplication
class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}
