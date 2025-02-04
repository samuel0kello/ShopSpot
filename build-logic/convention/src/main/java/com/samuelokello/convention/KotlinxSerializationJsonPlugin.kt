package com.samuelokello.convention

import com.samuelokello.convention.support.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinxSerializationJsonPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply the Kotlin serialization plugin
            pluginManager.apply("kotlinx-serialization-json")

            // Add the kotlinx-serialization-json dependency
            dependencies {
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
            }
        }
    }
}