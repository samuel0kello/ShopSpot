
plugins {
    `kotlin-dsl`
}

group = "com.samuelokello.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidApplicationCompose") {
            id = "com.samuelokello.convention.application.compose"
            implementationClass = "com.samuelokello.convention.AndroidApplicationComposeConventionPlugin"
        }

        create("androidApplication") {
            id = "com.samuelokello.convention.application"
            implementationClass = "com.samuelokello.convention.AndroidApplicationConventionPlugin"
        }

        create("androidLibrary") {
            id = "com.samuelokello.convention.library"
            implementationClass = "com.samuelokello.convention.AndroidLibraryConventionPlugin"
        }

        create("androidLibraryCompose") {
            id = "com.samuelokello.convention.library.compose"
            implementationClass = "com.samuelokello.convention.AndroidLibraryComposeConventionPlugin"
        }

        create("jvmLibrary") {
            id = "com.samuelokello.convention.jvm.library"
            implementationClass = "com.samujelokello.convention.JvmLibraryConventionPlugin"
        }
    }
}