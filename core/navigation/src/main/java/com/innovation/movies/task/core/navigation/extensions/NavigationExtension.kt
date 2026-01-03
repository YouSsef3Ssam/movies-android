package com.innovation.movies.task.core.navigation.extensions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlin.reflect.KClass
import kotlin.reflect.KType

const val ANIMATION_DURATION = 700

@Composable
fun InnovationNavHost(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    route: KClass<*>? = null,
    builder: NavGraphBuilder.() -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        contentAlignment = contentAlignment,
        route = route,
        builder = builder,
    )
}

inline fun <reified T : Any> NavGraphBuilder.innovationComposable(
    deepLinks: List<NavDeepLink> = emptyList(),
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enterTransition = { fadeIn(tween(ANIMATION_DURATION)) },
        exitTransition = { fadeOut(tween(ANIMATION_DURATION)) },
        popEnterTransition = { fadeIn(tween(ANIMATION_DURATION)) },
        popExitTransition = { fadeOut(tween(ANIMATION_DURATION)) },
        content = content,
    )
}
