dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.mongodb:mongodb-driver-sync")
    implementation("org.mongodb:mongodb-driver-core")
    implementation("org.mongodb:bson")
}
tasks {
    bootJar {
        enabled = false
    }
}