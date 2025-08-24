plugins {
    alias(libs.plugins.shopspot.android.library.compose)
}

android {
    namespace = "com.samuelokello.core.presentation.ui"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    api(libs.androidx.navigation.compose)
    implementation(project(":core:presentation:designsystem"))
    implementation(project(":core:domain"))

    implementation(project(":feat:auth"))
    implementation(project(":feat:cart"))
    implementation(project(":feat:favourite"))
    implementation(project(":feat:home"))
    implementation(project(":feat:order"))
    implementation(project(":feat:product"))
    implementation(project(":feat:profile"))
    implementation(project(":feat:search"))
}