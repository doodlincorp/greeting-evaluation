dependencies {
    implementation(project(":evaluation-business-domain"))
    implementation("org.springframework:spring-tx")
}
tasks {
    bootJar {
        enabled = false
    }
}