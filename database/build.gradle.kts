plugins {
    id("com.android.convention.library")
    id("com.samuelokello.convention.hilt") // Apply Hilt Convention Plugin
    id("com.samuelokello.convention.room") // Apply Room Convention Plugin
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.samuelokello.database"
}

dependencies {
    implementation(project(":core"))

    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

}
