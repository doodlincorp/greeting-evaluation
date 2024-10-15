dependencies {
    implementation(project(":concept-business-domain"))
    implementation(project(":concept-business-application"))
    implementation("doodlin.messaging:kafka:2.0.9")
}
tasks {
    bootJar {
        enabled = false
    }
}