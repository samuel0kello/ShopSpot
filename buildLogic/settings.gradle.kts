dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files(("../gradle/libs.versions.toml")))
        }
    }
}

rootProject.name = "buildLogic"
include(":convention")