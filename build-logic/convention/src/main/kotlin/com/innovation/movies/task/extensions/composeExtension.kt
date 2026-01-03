package com.innovation.movies.task.extensions

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureCompose() {
    extensions.getByType<BaseExtension>().apply {
        buildFeatures.compose = true
    }
}