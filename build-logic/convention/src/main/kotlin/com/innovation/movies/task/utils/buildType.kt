package com.innovation.movies.task.utils

private interface BuildType {
    val isMinifyEnabled: Boolean
    val isShrinkResources: Boolean
    val isDebuggable: Boolean
}

internal object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isShrinkResources: Boolean = false
    override val isDebuggable: Boolean = true
}

internal object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean = true
    override val isShrinkResources: Boolean = true
    override val isDebuggable: Boolean = false
}