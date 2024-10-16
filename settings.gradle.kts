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

rootProject.name = "greeting-evaluation"

include(":evaluation-adaptor-kafka")
include(":evaluation-adaptor-mongo")
include(":evaluation-adaptor-mysql")
include(":evaluation-adaptor-redis")
include(":evaluation-adaptor-aws")
include(":evaluation-business-application")
include(":evaluation-business-domain")
include(":evaluation-presentation-api")
include(":evaluation-presentation-batch")
//include(":evaluation-presentation-consumer")

project(":evaluation-adaptor-kafka").projectDir = file("greeting-evaluation/adaptor/kafka")
project(":evaluation-adaptor-mongo").projectDir = file("greeting-evaluation/adaptor/mongo")
project(":evaluation-adaptor-mysql").projectDir = file("greeting-evaluation/adaptor/mysql")
project(":evaluation-adaptor-redis").projectDir = file("greeting-evaluation/adaptor/redis")
project(":evaluation-adaptor-aws").projectDir = file("greeting-evaluation/adaptor/aws")
project(":evaluation-business-application").projectDir = file("greeting-evaluation/business/application")
project(":evaluation-business-domain").projectDir = file("greeting-evaluation/business/domain")
project(":evaluation-presentation-api").projectDir = file("greeting-evaluation/presentation/api")
project(":evaluation-presentation-batch").projectDir = file("greeting-evaluation/presentation/batch")
//project(":concept-presentation-consumer").projectDir = file("concept/presentation/consumer")