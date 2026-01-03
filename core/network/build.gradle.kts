import com.innovation.movies.task.utils.NameSpace

plugins {
    id(libs.plugins.innovation.android.library.get().pluginId)
    id(libs.plugins.innovation.linting.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.secrets)
}

secrets {
    defaultPropertiesFileName = "network.properties"
}

android {
    namespace = NameSpace.Core.NETWORK
    buildFeatures.buildConfig = true
}

dependencies {
    /** Json */
    implementation(libs.kotlinx.serialization.json)

    /** Koin */
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    /** Ktor - Network */
    api(libs.bundles.ktor)

    /** Pluto */
    debugImplementation(libs.bundles.pluto.debug)
    releaseImplementation(libs.bundles.pluto.release)
}
