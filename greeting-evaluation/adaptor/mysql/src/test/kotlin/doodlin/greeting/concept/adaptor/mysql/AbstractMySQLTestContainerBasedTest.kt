package doodlin.greeting.concept.adaptor.mysql

import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

/**
 * 테스트 컨테이너의 사용을 선언한다.
 */
@Testcontainers
abstract class AbstractMySQLTestContainerBasedTest {
    companion object {
        /**
         * mysql version을 선언한다.
         */
        private const val VERSION: String = "mysql:5.7"

        /**
         * 컨테이너를 선언한다.
         * 위와 같이 선언된 컨테이너는 테스트가 종료될 때 자동으로 shutdown 처리된다.
         */
        @Container
        val MYSQL_CONTAINER = MySQLContainer(VERSION)
    }
}
