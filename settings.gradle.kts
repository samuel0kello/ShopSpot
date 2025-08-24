pluginManagement {
    includeBuild("buildLogic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":buildLogic:convention:testClasses"))

rootProject.name = "ShopSpot"
include(":app")
include(":core:data")
include(":core:datasource:local")
include(":core:datasource:remote")
include(":core:domain")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":feat:auth")
include(":feat:home")
include(":feat:cart")
include(":feat:order")
include(":feat:product")
include(":feat:search")
include(":feat:profile")
include(":feat:favourite")
