package com.innovation.movies.task.utils

import org.gradle.api.JavaVersion

internal object ConfigData {

    const val COMPILE_SDK = 36
    const val TARGET_SDK = 36
    const val MIN_SDK = 24
    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val PROGUARD_FILE_NAME = "proguard-rules.pro"
    const val CONSUMER_FILE_NAME = "consumer-rules.pro"
    val SOURCE_COMPATIBILITY = JavaVersion.VERSION_22
    val TARGET_COMPATIBILITY = JavaVersion.VERSION_22
}