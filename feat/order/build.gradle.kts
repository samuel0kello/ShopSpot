plugins {
    alias(libs.plugins.shopspot.android.feature)
}

android {
    namespace = "com.samuelokello.feat.order"
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
    implementation(project(":core:domain"))
    implementation(project(":core:presentation:designsystem"))
}