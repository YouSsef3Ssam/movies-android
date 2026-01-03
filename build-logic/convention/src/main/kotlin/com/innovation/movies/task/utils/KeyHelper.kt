package com.innovation.movies.task.utils

import java.io.File
import java.io.FileInputStream
import java.util.Properties

internal object KeyHelper {

    internal enum class Keys(val value: String) {
        KEY_STORE_FILE("storeFile"),
        KEY_STORE_PASSWORD("storePassword"),
        KEY_ALIAS("keyAlias"),
        KEY_PASSWORD("keyPassword"),
    }

    private val properties by lazy {
        var propertiesFile = File("innovation-movies-app-key.properties")
        if (!propertiesFile.exists()) {
            propertiesFile = File("innovation-movies-app-testing-key.properties")
        }
        Properties().apply { load(FileInputStream(propertiesFile)) }
    }

    fun getValue(key: Keys): String = properties.getProperty(key.value)

}