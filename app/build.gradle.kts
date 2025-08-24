plugins {
    alias(libs.plugins.shopspot.android.application)
    alias(libs.plugins.shopspot.android.application.compose)
}

android {
    namespace = "com.samuelokello.shopspot"

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    implementation(project(":core:presentation:designsystem"))
    implementation(project(":core:presentation:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:datasource:local"))
    implementation(project(":core:datasource:remote"))
    implementation(project(":core:data"))
}