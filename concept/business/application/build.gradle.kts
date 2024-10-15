dependencies {
    implementation(project(":concept-business-domain"))
    implementation("org.springframework:spring-tx")
}
tasks {
    bootJar {
        enabled = false
    }
}