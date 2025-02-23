plugins {
    id("com.samuelokello.convention.feature")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.samuelokello.feature.auth"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
