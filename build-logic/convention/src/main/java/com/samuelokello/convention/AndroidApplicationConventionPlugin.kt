package com.samuelokello.convention

import com.android.build.api.dsl.ApplicationExtension
import com.samuelokello.convention.support.AppConfig
import com.samuelokello.convention.support.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android") // Ensure project build.gradle declared this plugin
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AppConfig.targetSdk
            }
        }
    }

}