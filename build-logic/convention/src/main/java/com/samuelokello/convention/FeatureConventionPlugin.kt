package com.samuelokello.convention

import com.android.build.gradle.LibraryExtension
import com.samuelokello.convention.support.AppConfig
import com.samuelokello.convention.support.configureKotlinAndroid
import com.samuelokello.convention.support.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("com.samuelokello.convention.hilt")
                apply("com.android.convention.library.compose")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AppConfig.targetSdk
            }

            configurations.configureEach {
                resolutionStrategy {
                    force(libs.findLibrary("junit").get())
                    // Temporary workaround for https://issuetracker.google.com/174733673
                    force("org.objenesis:objenesis:2.6")
                }
            }

            dependencies {
                // Feature modules
                "implementation" (project(":core"))
                "implementation"(project(":common-ui"))

                // AndroidX dependencies
                "implementation"(libs.findLibrary("androidx-core-ktx").get())
                "implementation"(libs.findLibrary("androidx-appcompat").get())

                // Coroutines
                "implementation"(libs.findLibrary("kotlinx-coroutines-core").get())
                "testImplementation"(libs.findLibrary("kotlinx-coroutines-test").get())

                // material
                "implementation"(libs.findLibrary("androidx-material3").get())
                "implementation"(libs.findLibrary("material").get())

                // Compose Dependencies
                "implementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
                "implementation"(libs.findLibrary("androidx-appcompat").get())
                "implementation"(libs.findLibrary("androidx-ui").get())
                "implementation"(libs.findLibrary("androidx-compose-material").get())
                "implementation"(libs.findLibrary("androidx-ui-tooling").get())
                "implementation"(libs.findLibrary("androidx-ui-tooling-preview").get())

                // coil
                "implementation"(libs.findLibrary("coil-compose").get())
            }
        }
    }
}
