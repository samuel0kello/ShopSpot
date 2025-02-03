plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.samuelokello.shopspot"
}

dependencies {
    implementation(project(":core"))
    
    implementation(libs.retrofit)
    implementation(libs.okhttp)

    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)

    implementation (libs.kotlinx.coroutines.core)

    implementation(libs.androidx.junit.ktx)

}
