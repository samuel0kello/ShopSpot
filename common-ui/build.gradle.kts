plugins {
    id("com.android.convention.application")
    id("com.android.convention.application.compose")
    id("com.samuelokello.convention.ksp")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.samuelokello.android.hilt)
}

android {
    namespace = "com.samuelokello.shopspot"
}


dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)

    //extended icons
    implementation(libs.androidx.material.icons.extended.android)
    implementation(project(":core"))
}
