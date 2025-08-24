plugins {
    alias(libs.plugins.shopspot.android.library)
    alias(libs.plugins.shopspot.jvm.ktor)
}

android {
    namespace = "com.samuelokello.datasource.remote"

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
//    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}