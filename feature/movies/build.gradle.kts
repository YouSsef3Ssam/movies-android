import com.innovation.movies.task.utils.NameSpace

plugins {
    id(libs.plugins.innovation.android.library.get().pluginId)
    id(libs.plugins.innovation.compose.get().pluginId)
    id(libs.plugins.innovation.linting.get().pluginId)
    id(libs.plugins.innovation.testing.unit.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = NameSpace.Feature.MOVIES
}

dependencies {
    /** Core Modules */
    implementation(projects.core.ui)
    implementation(projects.core.navigation)
    implementation(projects.core.localization)
    implementation(projects.core.common)
    implementation(projects.core.network)

    /** Koin */
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    /** Storage */
    implementation(libs.bundles.storage.room)
    ksp(libs.room.compiler)

    /** Paging */
    implementation(libs.compose.paging)

    /** Pluto Room Database */
    debugImplementation(libs.pluto.room.debug)
    releaseImplementation(libs.pluto.room.release)

    /** Unit Testing */
    testImplementation(libs.paging.runtime.ktx)
}
