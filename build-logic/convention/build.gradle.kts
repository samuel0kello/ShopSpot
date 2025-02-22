
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
            id = "com.android.convention.application.compose"
            implementationClass = "com.samuelokello.convention.AndroidApplicationComposeConventionPlugin"
        }

        create("androidApplication") {
            id = "com.android.convention.application"
            implementationClass = "com.samuelokello.convention.AndroidApplicationConventionPlugin"
        }

        create("androidLibrary") {
            id = "com.android.convention.library"
            implementationClass = "com.samuelokello.convention.AndroidLibraryConventionPlugin"
        }

        create("androidLibraryCompose") {
            id = "com.android.convention.library.compose"
            implementationClass = "com.samuelokello.convention.AndroidLibraryComposeConventionPlugin"
        }

        create("kotlinLibrary") {
            id = "com.samuelokello.convention.kotlin"
            implementationClass = "com.samuelokello.convention.KotlinConventionPlugin"
        }

        create("hilt") {
            id = "com.samuelokello.convention.hilt"
            implementationClass = "com.samuelokello.convention.AndroidHiltConventionPlugin"
        }

        create("ksp") {
            id = "com.samuelokello.convention.ksp"
            implementationClass = "com.samuelokello.convention.KspConventionPlugin"
        }

        create("kotlinxSerializationJson") {
            id = "com.samuelokello.convention.kotlinxSerializationJson"
            implementationClass = "com.samuelokello.convention.KotlinxSerializationJsonPlugin"
        }

        create("room") {
            id = "com.samuelokello.convention.room"
            implementationClass = "com.samuelokello.convention.RoomConventionPlugin"
        }

        create("feature-convention") {
            id = "com.samuelokello.convention.feature"
            implementationClass = "com.samuelokello.convention.FeatureConventionPlugin"
        }

        create("sources-convention") {
            id = "com.samuelokello.convention.sources"
            implementationClass = "com.samuelokello.convention.SourcesConventionPlugin"
        }
    }
}
