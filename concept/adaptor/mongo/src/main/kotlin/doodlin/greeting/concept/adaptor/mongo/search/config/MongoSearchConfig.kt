package doodlin.greeting.concept.adaptor.mongo.search.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.net.URLEncoder

@Configuration
@EnableMongoRepositories(
    basePackages = ["doodlin.greeting.concept.mongo.search.repository"],
    mongoTemplateRef = "mongoSearchTemplate"
)
internal class MongoSearchConfig {
    @Bean(name = ["mongoSearchProperties"])
    @ConfigurationProperties(prefix = "spring.data.mongodb.search")
    fun mongoSearchProperties(): MongoProperties {
        return MongoProperties()
    }

    @Bean(name = ["mongoSearchClient"])
    fun mongoSearchClient(@Qualifier("mongoSearchProperties") mongoProperties: MongoProperties): MongoClient {
        val uri = "mongodb+srv://${mongoProperties.username}:${
            URLEncoder.encode(
                mongoProperties.password.concatToString(),
                "UTF-8"
            )
        }@${mongoProperties.host}/${mongoProperties.database}" +
                "?retryWrites=true&w=majority&authSource=${mongoProperties.authenticationDatabase}"
        val connectionString = ConnectionString(uri)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()

        return MongoClients.create(mongoClientSettings)
    }

    @Bean(name = ["mongoSearchDBFactory"])
    fun mongoSearchDBFactory(
        @Qualifier("mongoSearchClient") mongoClient: MongoClient,
        @Qualifier("mongoSearchProperties") mongoProperties: MongoProperties,
    ): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(
            mongoClient,
            mongoProperties.database
        )
    }

    @Bean(name = ["mongoSearchTemplate"])
    fun mongoSearchTemplate(
        @Qualifier("mongoSearchDBFactory") mongoDatabaseFactory: MongoDatabaseFactory,
        @Qualifier("mongoCustomConversions") mongoCustomConversions: MongoCustomConversions,
    ): MongoTemplate {
        val mongoTemplate = MongoTemplate(mongoDatabaseFactory)
        val converter = mongoTemplate.converter as MappingMongoConverter
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        converter.setCustomConversions(mongoCustomConversions)
        converter.afterPropertiesSet()

        return mongoTemplate
    }
}
