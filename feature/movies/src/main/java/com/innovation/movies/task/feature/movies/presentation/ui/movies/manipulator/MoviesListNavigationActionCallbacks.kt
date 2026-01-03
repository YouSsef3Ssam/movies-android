package com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator

import com.innovation.movies.task.core.navigation.entry.movieDetails.args.MovieArgs

sealed interface MoviesListNavigationActionCallbacks {
    class NavigateToDetails(
        val id: String,
        val movie: MovieArgs?,
    ) : MoviesListNavigationActionCallbacks
}
