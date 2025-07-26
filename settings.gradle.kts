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

rootProject.name = "ShopSpot"
include(":app")
include(":core")
//include(":core:data")
include(":core:data")
include(":core:datasource")
include(":core:datasource:local")
include(":core:datasource:remote")
include(":core:domain")
include(":core:presentation")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
