package com.innovation.movies.task.core.navigation.extensions

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import timber.log.Timber

fun NavController?.popBackSafe() {
    if (this == null) return
    try {
        navigateUp()
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun <T : Any> NavController?.popUntil(
    route: T,
    inclusive: Boolean = false,
) {
    if (this == null) return
    try {
        popBackStack(route = route, inclusive = inclusive)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun <T : Any> NavController?.navigateSafe(route: T) {
    if (this == null) return
    try {
        navigate(route)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun <T : Any> NavController?.navigateSafe(
    route: T,
    builder: NavOptionsBuilder.() -> Unit,
) {
    if (this == null) return
    try {
        navigate(route, builder)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

fun NavController?.navigateSafe(
    deepLink: Uri,
    navOptions: NavOptions? = null,
) {
    if (this == null) return
    try {
        navigate(deepLink = deepLink, navOptions = navOptions)
    } catch (e: Exception) {
        Timber.e(e)
    }
}
