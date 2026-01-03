package com.innovation.movies.task.feature.movies.presentation.ui.movies

import androidx.navigation.NavGraphBuilder
import com.innovation.movies.task.core.navigation.entry.MoviesEntry
import com.innovation.movies.task.core.navigation.extensions.innovationComposable
import com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesListNavigationActionCallbacks


fun NavGraphBuilder.moviesRoute(navigationCallbacks: (MoviesListNavigationActionCallbacks) -> Unit) {
    innovationComposable<MoviesEntry> {
        MoviesRoute(navigationCallbacks = navigationCallbacks)
    }
}
