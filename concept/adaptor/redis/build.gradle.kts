dependencies {
    val commonsPoolVersion: String by project
    implementation(project(":concept-business-domain"))
    implementation(project(":concept-business-application"))
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.apache.commons:commons-pool2:$commonsPoolVersion")
}
tasks {
    bootJar {
        enabled = false
    }
}