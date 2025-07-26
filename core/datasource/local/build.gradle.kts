plugins {
    alias(libs.plugins.shopspot.android.room)
}

android {
    namespace = "com.example.datasource.local"

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

//    implementation(libs.preference.datastore)
}