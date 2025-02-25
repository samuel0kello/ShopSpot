plugins {
    id("com.samuelokello.convention.sources")
}

android {
    namespace = "com.samuelokello.repository"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":sources:local"))
    implementation(project(":sources:network"))
}
