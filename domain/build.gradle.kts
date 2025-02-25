plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.convention.library")
    id("com.samuelokello.convention.hilt")
    id("com.samuelokello.convention.room")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.samuelokello.domain"
}

dependencies {
    implementation(project(":core"))

//    // Hilt for Dependency Injection
//    implementation(libs.dagger.hilt)
//    ksp(libs.dagger.hilt.compiler)

    // Coroutines for async operations
    implementation(libs.kotlinx.coroutines.core)
}
