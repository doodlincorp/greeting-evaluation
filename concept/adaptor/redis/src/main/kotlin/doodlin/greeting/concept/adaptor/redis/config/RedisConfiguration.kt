package doodlin.greeting.concept.adaptor.redis.config

import doodlin.greeting.concept.adaptor.redis.repository.crud.Repositories
import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisNode
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories(
    basePackageClasses = [Repositories::class]
)
@EnableConfigurationProperties(RedisProperties::class)
internal class RedisConfiguration {

    @Primary
    @Bean
    fun redisConnectionFactory(
        redisProperties: RedisProperties,
    ): RedisConnectionFactory {
        val redisClusterConfiguration: RedisClusterConfiguration =
            RedisClusterConfiguration().apply {
                clusterNode(RedisNode(redisProperties.host, redisProperties.port))
                username = redisProperties.username
                password = RedisPassword.of(redisProperties.password)
            }

        val lettucePoolingClientConfiguration: LettucePoolingClientConfiguration =
            LettucePoolingClientConfiguration.builder().poolConfig(
                GenericObjectPoolConfig<Any>().apply {
                    minIdle = redisProperties.lettuce.pool.minIdle
                    maxIdle = redisProperties.lettuce.pool.maxIdle
                    maxTotal = redisProperties.lettuce.pool.maxActive
                }
            ).apply {
                when (redisProperties.ssl.isEnabled) {
                    true -> this.useSsl()
                    false -> {}
                }
            }.build()

        return LettuceConnectionFactory(redisClusterConfiguration, lettucePoolingClientConfiguration)
    }
}
