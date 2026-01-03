package com.innovation.movies.task.feature.main.presentation.ui.main.composables.modules

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.innovation.movies.task.core.navigation.entry.movieDetails.MovieDetailsEntry
import com.innovation.movies.task.core.navigation.extensions.navigateSafe
import com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesListNavigationActionCallbacks
import com.innovation.movies.task.feature.movies.presentation.ui.movies.moviesRoute
import com.innovation.movies.task.feature.movies.presentation.ui.movieDetails.movieDetailsRoute

internal fun NavGraphBuilder.moviesModule(navController: NavController) {
    moviesRoute { action ->
        when (action) {
            is MoviesListNavigationActionCallbacks.NavigateToDetails -> {
                navController.navigateSafe(MovieDetailsEntry(id = action.id, movie = action.movie))
            }
        }
    }
    movieDetailsRoute()
}