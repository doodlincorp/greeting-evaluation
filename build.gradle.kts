import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm")
    id("com.google.cloud.tools.jib")
    id("org.jetbrains.kotlin.kapt")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.kotlin.plugin.jpa")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.spring")
}

tasks {
    bootJar {
        enabled = false
    }
    jar {
        enabled = true
    }

    val deletePreCommit = register<Delete>("deletePreCommit") {
        delete = setOf(".git/hooks/pre-commit")
    }

    val copyPreCommit = register<Copy>("copyPreCommit") {
        from(".githooks/pre-commit").into(".git/hooks")
    }

    build {
        dependsOn(deletePreCommit, copyPreCommit)
    }

    copyPreCommit {
        mustRunAfter(deletePreCommit)
    }
}

allprojects {
    group = "doodlin.greeting.evaluation"
    version = "1.0.0"

    repositories {
        mavenCentral()
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
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

tasks.withType<KotlinJvmCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

subprojects {
    val kotlinVersion: String by project
    val coroutinesVersion: String by project
    val springBootVersion: String by project
    val detektVersion: String by project
    val kluentVersion: String by project
    val junitJupiterVersion: String by project
    val logbackContribVersion: String by project
    val retrofitVersion: String by project
    val okHttpVersion: String by project
    val jacksonVersion: String by project
    val jakartaValidationApiVersion: String by project
    val fixtureMonkeyVersion: String by project
    val dataFakerVersion: String by project
    val mockitoKotlinVersion: String by project
    val mockkVersion: String by project

    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    tasks {
        withType<Test> {
            useJUnitPlatform()
            maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2)
        }

        compileKotlin {
            kotlinOptions.jvmTarget = "17"
        }

        compileTestKotlin {
            kotlinOptions.jvmTarget = "17"
        }

        clean {
            delete("out/")
            delete("build/")
        }

        detekt {
            version = detektVersion
            config = files("${rootProject.projectDir}/detekt.yml")
            autoCorrect = true
        }
    }

    allOpen {
        annotations(
            "jakarta.persistence.Entity"
        )
    }

    if (System.getProperty("os.arch") == "aarch64") {
        val target = file("${org.gradle.internal.jvm.Jvm.current().javaHome}/lib/libaws-crt-jni.dylib")
        if (!target.exists()) file("${rootProject.projectDir}/lib/libaws-crt-jni.dylib").copyTo(target)
    }

    dependencies {
        // kotlin
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")

        // spring
        implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-aop")

        // jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

        // logback
        implementation("ch.qos.logback.contrib:logback-jackson:$logbackContribVersion")
        implementation("ch.qos.logback.contrib:logback-json-classic:$logbackContribVersion")

        implementation("javax.inject:javax.inject:1")

        // Detekt
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")

        // logging
        implementation("doodlin.commons:logging:1.0.0")

        //validation
        implementation("jakarta.validation:jakarta.validation-api:$jakartaValidationApiVersion")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "junit")
            exclude(group = "org.junit.vintage")
        }

        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-mockito:$fixtureMonkeyVersion")
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:$fixtureMonkeyVersion")
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-jakarta-validation:$fixtureMonkeyVersion")
        testImplementation("net.datafaker:datafaker:$dataFakerVersion")
        testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")


        testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    }
}
