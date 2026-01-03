import com.innovation.movies.task.utils.NameSpace

plugins {
    id(libs.plugins.innovation.android.library.get().pluginId)
    id(libs.plugins.innovation.compose.get().pluginId)
    id(libs.plugins.innovation.linting.get().pluginId)
}

android {
    namespace = NameSpace.Feature.MAIN
}

dependencies {
    /** Core Modules */
    implementation(projects.core.ui)
    implementation(projects.core.navigation)

    /** Feature Modules */
    implementation(projects.feature.movies)

    /** Koin */
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
}
