package com.samuelokello.convention

import com.samuelokello.convention.support.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Check if the Android library plugin or the Java library plugin is applied
            pluginManager.withPlugin("com.android.library") {
                configureRoomDependencies()
            }
        }
    }

    private fun Project.configureRoomDependencies() {
        // Apply the KSP plugin if it's not already applied
        pluginManager.apply("com.google.devtools.ksp")

        dependencies {
            // Use the version catalog to get the dependencies
            add("implementation", libs.findLibrary("androidx.room.runtime").get())
            add("implementation", libs.findLibrary("androidx.room.ktx").get())
            add("ksp", libs.findLibrary("androidx.room.compiler").get())
        }
    }
}
