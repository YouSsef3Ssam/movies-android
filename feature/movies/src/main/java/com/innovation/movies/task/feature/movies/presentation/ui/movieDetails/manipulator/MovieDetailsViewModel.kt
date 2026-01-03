package com.innovation.movies.task.feature.movies.presentation.ui.movieDetails.manipulator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.innovation.movies.task.core.common.extensions.decode
import com.innovation.movies.task.core.navigation.entry.movieDetails.args.MovieArgs
import com.innovation.movies.task.feature.movies.presentation.mapper.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json

internal class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    json: Json,
) : ViewModel() {
    private val movieId = savedStateHandle.get<String>("id")
    private val movie: MovieArgs? =
        savedStateHandle.get<String>("movie")
            ?.run { json.decode(this) }

    private val _state: MutableStateFlow<MovieDetailsState> =
        MutableStateFlow(MovieDetailsState(movie = movie?.toUI()))
    val state: StateFlow<MovieDetailsState> = _state.asStateFlow()

    init {
        if (movie == null) {
            // TODO("Fetch pos details From the remote using movieId")
        }
    }
}
