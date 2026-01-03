package com.innovation.movies.task.feature.movies.presentation.ui.movieDetails

import androidx.navigation.NavGraphBuilder
import com.innovation.movies.task.core.navigation.entry.movieDetails.MovieDetailsEntry
import com.innovation.movies.task.core.navigation.extensions.innovationComposable

fun NavGraphBuilder.movieDetailsRoute() {
    innovationComposable<MovieDetailsEntry>(typeMap = MovieDetailsEntry.typeMap) {
        MovieDetailsRoute()
    }
}
