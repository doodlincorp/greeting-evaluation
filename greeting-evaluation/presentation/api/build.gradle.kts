import com.google.cloud.tools.jib.gradle.JibExtension

plugins {
    id("com.google.cloud.tools.jib")
}

dependencies {
    val springDocVersion: String by project

    implementation("doodlin.commons:spring-boot-starter-parameter-store:0.1.0")
    implementation("doodlin.commons:spring-boot-starter-http:0.1.2")

    implementation(project(":evaluation-business-domain"))
    implementation(project(":evaluation-business-application"))

    implementation(project(":evaluation-adaptor-kafka"))
    implementation(project(":evaluation-adaptor-mysql"))
    implementation(project(":evaluation-adaptor-mongo"))
    implementation(project(":evaluation-adaptor-redis"))
    implementation(project(":evaluation-adaptor-aws"))

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")
    implementation("org.springframework.boot:spring-boot-starter-web")
}

val stage: String? by project
val awsAccount: String? by project
val imageTag: String? by project
val serviceName: String? by project

tasks {
    configure<JibExtension> {
        val ecrRepositoryName = when (stage) {
            "dev" -> "$serviceName-dev"
            "stage" -> "$serviceName-stage"
            "prod" -> "$serviceName"
            else -> "$serviceName-local"
        }

        from {
            image = "148109653756.dkr.ecr.ap-northeast-2.amazonaws.com/doodlin-base:amazoncorretto-17-alpine"
            setCredHelper("ecr-login")
        }

        to {
            image = "${awsAccount ?: "148109653756"}.dkr.ecr.ap-northeast-2.amazonaws.com/$ecrRepositoryName"
            setCredHelper("ecr-login")
            tags = setOf(imageTag ?: "tag", "latest")
        }

        container {
            val otelFlags = listOf(
                "-javaagent:/doodlin/opentelemetry-javaagent.jar",
                "-Dotel.javaagent.extensions=/doodlin/extension-1.0-all.jar",
                "-Dotel.service.name=$serviceName",
                "-Dotel.traces.sampler=doodlin"
            )

            val jmxPrometheusFlags = listOf(
                "-javaagent:/doodlin/jmx_prometheus_javaagent-0.20.0.jar=9404:/doodlin/jmx_config.yaml"
            )

            val memory = when (stage) {
                "dev" -> "2g"
                else -> "4g"
            }

            jvmFlags = listOf(
                "-server",
                "-Xms$memory",
                "-Xmx$memory",
                "-XX:MaxMetaspaceSize=512m",
                "-XX:+UseContainerSupport",
                "-XX:+UseG1GC",
                "-XX:MaxGCPauseMillis=200",
                "-XX:+UnlockExperimentalVMOptions",
                "-XX:+UnlockDiagnosticVMOptions",
                "-XX:+EnableJVMCI",
                "-XX:+UseJVMCICompiler",
                "-XX:NativeMemoryTracking=detail",
                "-XX:+ParallelRefProcEnabled",
                "-XX:-ResizePLAB"
            ) + otelFlags + jmxPrometheusFlags

            environment = mapOf(
                "SPRING_PROFILES_ACTIVE" to (stage ?: "dev"),
            )
            ports = listOf("8080")
        }
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
