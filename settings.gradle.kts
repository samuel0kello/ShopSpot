pluginManagement {
    includeBuild("build-logic") {
        name = "build-logic-included"
    }
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
include(":feature:auth")
include(":feature:cart")
include(":feature:products")
include(":feature:profile")
include(":feature:wishlist")
include(":build-logic:convention")
