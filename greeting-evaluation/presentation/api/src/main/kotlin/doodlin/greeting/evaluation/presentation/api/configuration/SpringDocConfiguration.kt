package doodlin.greeting.evaluation.presentation.api.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfiguration {
    @Bean
    fun openApi(): OpenAPI = OpenAPI()
        .components(components())
        .info(info())

    private fun components() = Components()

    private fun info() = Info()
        .title("Greeting Concept Domain API Document")
        .description("그리팅 Concept 도메인 API 문서")
        .version("1.0.0")
        .contact(contact())

    private fun contact() = Contact()
        .name("sungwonkim")
        .email("sungwonkim@doodlin.co.kr")
}
