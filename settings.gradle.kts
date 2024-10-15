pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings

    val kotlinJvmPluginVersion: String by settings
    val jibPluginVersion: String by settings
    val detektPluginVersion: String by settings

    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            url =
                uri("https://maven.pkg.github.com/doodlincorp/greeting-access-log-java")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        maven {
            url =
                uri("https://maven.pkg.github.com/doodlincorp/maven-artifact")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    plugins {
        kotlin("jvm") version kotlinJvmPluginVersion
        id("com.google.cloud.tools.jib") version jibPluginVersion
        id("org.jetbrains.kotlin.kapt") version kotlinVersion
        id("io.gitlab.arturbosch.detekt") version detektPluginVersion
        id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
        id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    }
}

rootProject.name = "spring-boot-template"

include(":concept-adaptor-kafka")
include(":concept-adaptor-mongo")
include(":concept-adaptor-mysql")
include(":concept-adaptor-redis")
include(":concept-adaptor-aws")
include(":concept-business-application")
include(":concept-business-domain")
include(":concept-presentation-api")
include(":concept-presentation-batch")
include(":concept-presentation-consumer")

project(":concept-adaptor-kafka").projectDir = file("concept/adaptor/kafka")
project(":concept-adaptor-mongo").projectDir = file("concept/adaptor/mongo")
project(":concept-adaptor-mysql").projectDir = file("concept/adaptor/mysql")
project(":concept-adaptor-redis").projectDir = file("concept/adaptor/redis")
project(":concept-adaptor-aws").projectDir = file("concept/adaptor/aws")
project(":concept-business-application").projectDir = file("concept/business/application")
project(":concept-business-domain").projectDir = file("concept/business/domain")
project(":concept-presentation-api").projectDir = file("concept/presentation/api")
project(":concept-presentation-batch").projectDir = file("concept/presentation/batch")
project(":concept-presentation-consumer").projectDir = file("concept/presentation/consumer")