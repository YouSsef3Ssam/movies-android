import com.innovation.movies.task.utils.NameSpace

plugins {
    id(libs.plugins.innovation.android.library.get().pluginId)
    id(libs.plugins.innovation.compose.get().pluginId)
    id(libs.plugins.innovation.linting.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = NameSpace.Core.COMMON
}

dependencies {
    /** Json */
    implementation(libs.kotlinx.serialization.json)

    /** Koin */
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
}
