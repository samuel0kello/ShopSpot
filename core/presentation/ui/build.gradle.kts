plugins {
    alias(libs.plugins.shopspot.android.library.compose)
}

android {
    namespace = "com.example.core.presentation.ui"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    implementation(project(":core:presentation:designsystem"))
}