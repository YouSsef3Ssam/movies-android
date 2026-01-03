import com.innovation.movies.task.utils.NameSpace


plugins {
    id(libs.plugins.innovation.android.application.get().pluginId)
    id(libs.plugins.innovation.compose.get().pluginId)
    id(libs.plugins.innovation.testing.unit.get().pluginId)
    id(libs.plugins.innovation.linting.get().pluginId)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.junit5.pluing)
}

android {
    namespace = NameSpace.APP

    defaultConfig {
        applicationId = NameSpace.APPLICATION_ID
    }
    buildFeatures.buildConfig = true
}

dependencies {
    /** Core */
    implementation(projects.core.ui)

    /** Feature */
    implementation(projects.feature.main)

    /** Utils */
    implementation(libs.coil)

    /** Json */
    implementation(libs.kotlinx.serialization.json)

    /** Koin */
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    /** Pluto */
    debugImplementation(libs.bundles.pluto.debug)
    releaseImplementation(libs.bundles.pluto.release)
}
