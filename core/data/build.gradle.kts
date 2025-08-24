plugins {
    alias(libs.plugins.shopspot.android.library)
}

android {
    namespace = "com.samuelokello.core.data"

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
    implementation(project(":core:datasource:remote"))
    implementation(project(":core:datasource:local"))
}