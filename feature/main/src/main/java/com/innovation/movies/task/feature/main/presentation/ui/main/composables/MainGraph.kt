package com.innovation.movies.task.feature.main.presentation.ui.main.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.innovation.movies.task.core.navigation.entry.MainEntry
import com.innovation.movies.task.core.navigation.entry.MoviesEntry
import com.innovation.movies.task.core.navigation.extensions.InnovationNavHost
import com.innovation.movies.task.feature.main.presentation.ui.main.composables.modules.moviesModule

@Composable
internal fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    InnovationNavHost(
        modifier = modifier,
        navController = navController,
        route = MainEntry::class,
        startDestination = MoviesEntry,
    ) {
        moviesModule(navController = navController)
    }
}
