rootProject.name = "youssef-essam-task"

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
pluginManagement {
    includeBuild("build-logic")
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
        maven("https://jitpack.io")
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

include(":app")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    // Core
    ":core:common",
    ":core:network",
    ":core:ui",
    ":core:navigation",
    ":core:localization",

    // Feature
    ":feature:main",
    ":feature:movies",
)
