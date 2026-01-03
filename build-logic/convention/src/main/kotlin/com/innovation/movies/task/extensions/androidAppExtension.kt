package com.innovation.movies.task.extensions

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.innovation.movies.task.utils.BuildTypeDebug
import com.innovation.movies.task.utils.BuildTypeRelease
import com.innovation.movies.task.utils.ConfigData
import com.innovation.movies.task.utils.KeyHelper
import com.innovation.movies.task.utils.Release
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureAndroidApp() {
    extensions.configure<BaseAppModuleExtension> {
        compileSdk = ConfigData.COMPILE_SDK

        defaultConfig {
            targetSdk = ConfigData.TARGET_SDK
            minSdk = ConfigData.MIN_SDK
            versionCode = Release.VERSION_CODE
            versionName = Release.VERSION_NAME
            testInstrumentationRunner = ConfigData.TEST_RUNNER
        }

        val releaseSigningConfig = "release"
        signingConfigs {
            create(releaseSigningConfig) {
                with(project) {
                    storeFile = file(KeyHelper.getValue(KeyHelper.Keys.KEY_STORE_FILE))
                }
                storePassword = KeyHelper.getValue(KeyHelper.Keys.KEY_STORE_PASSWORD)
                keyAlias = KeyHelper.getValue(KeyHelper.Keys.KEY_ALIAS)
                keyPassword = KeyHelper.getValue(KeyHelper.Keys.KEY_PASSWORD)
            }
        }
        buildTypes {
            debug {
                isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
                isShrinkResources = BuildTypeDebug.isShrinkResources
                isDebuggable = BuildTypeDebug.isDebuggable
            }
            release {
                isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
                isShrinkResources = BuildTypeRelease.isShrinkResources
                isDebuggable = BuildTypeRelease.isDebuggable

                signingConfig = signingConfigs.getByName(releaseSigningConfig)

                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    ConfigData.PROGUARD_FILE_NAME
                )
            }
        }

        compileOptions {
            sourceCompatibility = ConfigData.SOURCE_COMPATIBILITY
            targetCompatibility = ConfigData.TARGET_COMPATIBILITY
        }
    }
}
