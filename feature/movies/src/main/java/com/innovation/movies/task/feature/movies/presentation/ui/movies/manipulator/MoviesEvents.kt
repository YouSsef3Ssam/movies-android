package com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator

import com.innovation.movies.task.feature.movies.presentation.model.MovieUI

internal sealed interface MoviesEvents {
    class MovieClicked(val movie: MovieUI) : MoviesEvents

    object NavigationCallbackHandled : MoviesEvents
}
