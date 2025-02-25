plugins {
    id("com.android.convention.application")
    id("com.android.convention.application.compose")
    id("com.samuelokello.convention.hilt")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.samuelokello.shopspot"

    defaultConfig {
        applicationId = "com.samuelokello.shopspot"
    }
}

dependencies {

    implementation(project(":common-ui"))
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":sources:network"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:bottom-navigation"))
    implementation(project(":feature:cart"))
    implementation(project(":feature:home"))
    implementation(project(":feature:order"))
    implementation(project(":feature:products"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:search"))
    implementation(project(":feature:wishlist"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
