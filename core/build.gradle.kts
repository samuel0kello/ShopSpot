plugins {
    id("com.google.devtools.ksp")
    id("com.android.convention.library")
    id("com.samuelokello.convention.hilt")
    id("com.samuelokello.convention.room")
}

android {
    namespace = "com.samuelokello.shopspot.core"
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.datastore.preferences)

    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)
}
