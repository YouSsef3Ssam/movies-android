package com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.innovation.movies.task.feature.movies.domain.repository.MoviesRepository
import com.innovation.movies.task.feature.movies.presentation.mapper.toArgs
import com.innovation.movies.task.feature.movies.presentation.mapper.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

internal class MoviesViewModel(
    repository: MoviesRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state.asStateFlow()

    val moviesPagingFlow =
        repository
            .getMovies()
            .catch { }
            .map { paging ->
                paging.map { it.toUI() }
            }
            .cachedIn(viewModelScope)

    fun onEvent(event: MoviesEvents) {
        when (event) {
            MoviesEvents.NavigationCallbackHandled -> {
                _state.update { it.copy(callback = null) }
            }

            is MoviesEvents.MovieClicked -> {
                _state.update {
                    it.copy(
                        callback =
                            MoviesListNavigationActionCallbacks.NavigateToDetails(
                                id = event.movie.id,
                                movie = event.movie.toArgs(),
                            ),
                    )
                }
            }
        }
    }
}
