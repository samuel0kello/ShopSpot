plugins {
    id("com.samuelokello.convention.feature")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.samuelokello.home"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
