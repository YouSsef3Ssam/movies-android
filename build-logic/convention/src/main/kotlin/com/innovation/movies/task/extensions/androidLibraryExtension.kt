package com.innovation.movies.task.extensions

import com.android.build.gradle.LibraryExtension
import com.innovation.movies.task.utils.BuildTypeDebug
import com.innovation.movies.task.utils.BuildTypeRelease
import com.innovation.movies.task.utils.ConfigData
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureAndroidLibrary() {
    extensions.configure<LibraryExtension> {
        compileSdk = ConfigData.COMPILE_SDK

        defaultConfig {
            minSdk = ConfigData.MIN_SDK
            testInstrumentationRunner = ConfigData.TEST_RUNNER

            /** Common rules from plugin */
            val commonRules = layout.buildDirectory
                .file("generated/proguard/common-rules.pro")
                .get()
                .asFile

            commonRules.parentFile.mkdirs()

            commonRules.writeText(
                requireNotNull(
                    javaClass.classLoader
                        .getResource("proguard/common-rules.pro")
                ).readText()
            )

            consumerProguardFiles(commonRules)
            consumerProguardFile(ConfigData.CONSUMER_FILE_NAME)
        }

        buildTypes {
            debug {
                isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            }
            release {
                isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
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

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

