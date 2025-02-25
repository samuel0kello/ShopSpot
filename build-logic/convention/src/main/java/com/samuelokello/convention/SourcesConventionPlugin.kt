package com.samuelokello.convention

import com.android.build.api.dsl.LibraryExtension
import com.samuelokello.convention.support.AppConfig
import com.samuelokello.convention.support.configureKotlinAndroid
import com.samuelokello.convention.support.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("UnstableApiUsage")
class SourcesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.samuelokello.convention.hilt")
                apply("com.samuelokello.convention.room")
//                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.minSdk = AppConfig.minSdk
                testOptions.targetSdk = AppConfig.targetSdk
            }

            configurations.configureEach {
            }

            dependencies {
                "implementation" (project(":core"))

                // junit
                "implementation" (libs.findLibrary("androidx-junit-ktx").get())
                "implementation" (libs.findLibrary("androidx-junit").get())
                "implementation" (libs.findLibrary("junit").get())

                // okhttp
                "implementation" (libs.findLibrary("okhttp").get())

                // kotlin serialization
                "implementation" (libs.findLibrary("kotlinx-serialization-json").get())

                // kotlin coroutines
                "implementation" (libs.findLibrary("kotlinx-coroutines-core").get())

                // retrofit
                "implementation" (libs.findLibrary("retrofit").get())
                "implementation" (libs.findLibrary("retrofit2-kotlinx-serialization-converter").get())

                "implementation" (libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}
