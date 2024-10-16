dependencies {
    val awsKotlinSdkVersion: String by project
    val awsSoftwareSdkVersion: String by project
    val awsSecretManagerSdkVersion: String by project
    val awsCrtVersion: String by project

    implementation(project(":evaluation-business-domain"))
    implementation(project(":evaluation-business-application"))
    implementation("aws.sdk.kotlin:s3:$awsKotlinSdkVersion")
    implementation("software.amazon.awssdk:s3:$awsSoftwareSdkVersion")
    implementation("software.amazon.awssdk:sts:$awsSoftwareSdkVersion")
    runtimeOnly("software.amazon.awssdk:bom:$awsSoftwareSdkVersion")
    implementation("software.amazon.awssdk:secretsmanager:$awsSecretManagerSdkVersion")
    implementation("aws.sdk.kotlin:ssm:$awsKotlinSdkVersion")
    implementation("software.amazon.awssdk.crt:aws-crt") {
        version {
            strictly(awsCrtVersion)
        }
    }
}
tasks {
    bootJar {
        enabled = false
    }
}