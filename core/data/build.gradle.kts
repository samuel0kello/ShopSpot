plugins {
    alias(libs.plugins.shopspot.android.library)
}

android {
    namespace = "com.example.core.data"

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

}