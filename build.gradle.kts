// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.compose.plugin)
    }
}

plugins {
    alias(libs.plugins.innovation.android.application) apply false
    alias(libs.plugins.innovation.android.library) apply false
    alias(libs.plugins.innovation.linting) apply false
    alias(libs.plugins.innovation.compose) apply false
    alias(libs.plugins.innovation.testing.unit) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.android.secrets) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.junit5.pluing) apply false
}