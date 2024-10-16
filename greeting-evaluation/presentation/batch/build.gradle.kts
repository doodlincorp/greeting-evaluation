import com.google.cloud.tools.jib.gradle.JibExtension

plugins {
    id("com.google.cloud.tools.jib")
}

dependencies {
    implementation("doodlin.commons:spring-boot-starter-parameter-store:0.1.0")
    implementation(project(":evaluation-business-domain"))
    implementation(project(":evaluation-business-application"))
    implementation(project(":evaluation-adaptor-mysql"))
    implementation("org.springframework.boot:spring-boot-starter-batch")

    testImplementation("org.springframework.batch:spring-batch-test")
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
            )

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
