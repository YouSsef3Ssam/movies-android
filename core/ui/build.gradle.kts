import com.innovation.movies.task.utils.NameSpace

plugins {
    id(libs.plugins.innovation.android.library.get().pluginId)
    id(libs.plugins.innovation.compose.get().pluginId)
    id(libs.plugins.innovation.linting.get().pluginId)
}

android {
    namespace = NameSpace.Core.UI
}

dependencies {
    /** Core Modules */
    implementation(projects.core.common)
    implementation(projects.core.navigation)
    implementation(projects.core.localization)

    /** UI */
    implementation(libs.splash.screen)
    implementation(libs.material.icons.extended)

    /** Coil */
    implementation(libs.coil)

    /** Koin */
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
}
