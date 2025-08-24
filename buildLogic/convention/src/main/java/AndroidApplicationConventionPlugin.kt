import com.android.build.api.dsl.ApplicationExtension
import com.example.convention.configureKotlinAndroid
import com.example.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 *
 * This plugin sets up an Android application project with the necessary plugins and configurations,
 * including Kotlin support, dependency management, and specific Android settings.
 *
 *
 * The `AndroidApplicationConventionPlugin` class implements the `Plugin<Project>` interface in Gradle,
 * which allows it to be applied to a Gradle project.
 *
 * This plugin is designed to configure Android application projects with specific settings and plugins.
 *
 * The `apply` method is overridden to define the plugin's behavior when it is applied to a project.
 * Inside this method, the `with(target)` block is used to operate on the `Project` instance
 * passed as the `target` parameter.
 *
 *  `class AndroidApplicationConventionPlugin : Plugin<Project> {
 *     override fun apply(target: Project) {
 *         with(target) {
 *             // Configuration code
 *         }
 *     }
 *  }`
 *
 *  Within the `with(target)` block, the `pluginManager` is used to apply several plugins to the project.
 *  These plugins include `com.android.application` for Android application support,
 *  `org.jetbrains.kotlin.android` for Kotlin support, and
 *
 *   `with(pluginManager) {
 *     apply("com.android.application")
 *     apply("org.jetbrains.kotlin.android")
 *   }`
 *
 *  Next, the `extensions.configure<ApplicationExtension>` method is used to
 *  configure the `ApplicationExtension` instance. This extension provides access to
 *  Android-specific configuration options. The `configureKotlinAndroid` function is called
 *  to apply Kotlin-specific configurations to the Android project.
 *
 *   `extensions.configure<ApplicationExtension> {
 *     configureKotlinAndroid(this)
 *     defaultConfig.targetSdk = libs
 *                 .findVersion("projectTargetSdkVersion")
 *                 .get()
 *                 .toString()
 *                 .toInt()
 *     @Suppress("UnstableApiUsage")
 *     testOptions.animationsDisabled = true
 *  }`
 *
 *  The `defaultConfig.targetSdk` property is set to `36` defined in version catalog file, which specifies the target SDK version for the project.
 *
 *  The `@Suppress("UnstableApiUsage")` annotation is used to suppress warnings about unstable API usage.
 *
 *  The `testOptions.animationsDisabled` property is set to `true` to disable animations during testing.
 *
 *  This plugin is designed to configure Android application projects with specific settings and plugins.
 *
 *  Within the `ApplicationExtension` configuration block,
 *  the targetSdk version is set to 36, and animations are disabled in test options to speed up tests.
 *
 *
 *
 * */

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk =
                    libs
                        .findVersion("projectTargetSdkVersion")
                        .get()
                        .toString()
                        .toInt()
                defaultConfig.minSdk =
                    libs
                        .findVersion("projectMinSdkVersion")
                        .get()
                        .toString()
                        .toInt()
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
            }
        }
    }
}
