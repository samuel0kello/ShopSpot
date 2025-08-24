plugins {
    alias(libs.plugins.shopspot.android.room)
}

android {
    namespace = "com.samuelokello.datasource.local"

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

    implementation(libs.androidx.datastore.preferences)

//    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}