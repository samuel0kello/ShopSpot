plugins {
    id("com.samuelokello.convention.feature")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.samuelokello.products"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
