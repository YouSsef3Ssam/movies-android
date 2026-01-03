import com.innovation.movies.task.utils.NameSpace

plugins {
    id(libs.plugins.innovation.android.library.get().pluginId)
    id(libs.plugins.innovation.compose.get().pluginId)
    id(libs.plugins.innovation.linting.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
}

android {
    namespace = NameSpace.Core.NAVIGATION
}

dependencies {
    /** UI */
    implementation(projects.core.localization)
    api(libs.compose.navigation)

    /** Utils */
    implementation(libs.kotlinx.serialization.json)
}
