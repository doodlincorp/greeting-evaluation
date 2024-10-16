dependencies {
    implementation(project(":evaluation-business-domain"))
    implementation(project(":evaluation-business-application"))
    implementation("doodlin.messaging:kafka:2.0.9")
}
tasks {
    bootJar {
        enabled = false
    }
}