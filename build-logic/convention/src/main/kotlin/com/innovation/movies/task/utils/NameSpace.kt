package com.innovation.movies.task.utils

object NameSpace {
    const val APPLICATION_ID = "com.innovation.movies.task"
    const val APP = "$APPLICATION_ID.app"

    object Core {
        private const val CORE = "$APPLICATION_ID.core"
        const val UI = "$CORE.ui"
        const val LOCALIZATION = "$CORE.localization"
        const val NAVIGATION = "$CORE.core"
        const val COMMON = "$CORE.common"
        const val NETWORK = "$CORE.network"
    }

    object Feature {
        private const val FEATURE = "$APPLICATION_ID.feature"
        const val MAIN = "$FEATURE.main"
        const val MOVIES = "$FEATURE.movies"
    }
}