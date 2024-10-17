package doodlin.greeting.evaluation.adaptor.aws.configuration

import doodlin.greeting.evaluation.adaptor.aws.module.AwsS3Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient

@Configuration
internal class AwsConfiguration {
    @Bean
    fun s3Client(): S3Client {
        return S3Client.create()
    }

    @Bean
    fun secretsClient(): SecretsManagerClient {
        return SecretsManagerClient.builder()
            .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
            .region(Region.AP_NORTHEAST_2)
            .build()
    }

    @Bean
    fun awsS3Module(
        s3Client: S3Client,
    ): AwsS3Module {
        return AwsS3Module(s3Client = s3Client)
    }
}
