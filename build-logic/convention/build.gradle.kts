plugins {
    `kotlin-dsl`
}

group = "com.innovation.movies.task.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    implementation(libs.ktlint.gradle)
    implementation(libs.detekt.gradle)
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {
        register("innovationMoviesAndroidApplication") {
            id = "innovation.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("innovationMoviesAndroidLibrary") {
            id = "innovation.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("innovationMoviesCompose") {
            id = "innovation.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("innovationMoviesUnitTest") {
            id = "innovation.testing.unit"
            implementationClass = "UnitTestingConventionPlugin"
        }
        register("innovationMoviesLinting") {
            id = "innovation.linting"
            implementationClass = "LintingConventionPlugin"
        }
    }
}