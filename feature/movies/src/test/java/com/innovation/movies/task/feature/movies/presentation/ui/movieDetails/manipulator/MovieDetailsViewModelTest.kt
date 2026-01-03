package com.innovation.movies.task.feature.movies.presentation.ui.movieDetails.manipulator

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.innovation.movies.task.core.navigation.entry.movieDetails.args.MovieArgs
import com.innovation.movies.task.feature.movies.domain.mapper.toDomain
import com.innovation.movies.task.feature.movies.presentation.mapper.toArgs
import com.innovation.movies.task.feature.movies.presentation.mapper.toUI
import com.innovation.movies.task.feature.movies.utils.mockedMovieEntity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {
    private val savedStateHandle: SavedStateHandle = mockk()
    private val json = Json
    private lateinit var viewModel: MovieDetailsViewModel
    private val movieArgs = mockedMovieEntity.toDomain().toUI().toArgs()
    private val movieArgsString = json.encodeToString(MovieArgs.serializer(), movieArgs)

    private fun initViewModel(
        id: String? = movieArgs.id,
        movieJson: String? = movieArgsString,
    ) {
        every { savedStateHandle.get<String>("id") } returns id
        every { savedStateHandle.get<String>("movie") } returns movieJson
        viewModel = MovieDetailsViewModel(savedStateHandle = savedStateHandle, json = json)
    }

    @Test
    fun `state should contain movie details when movie args are provided`() =
        runTest {
            initViewModel()
            viewModel.state.test {
                val state = awaitItem()

                assertNotNull(state.movie)
                assertEquals(movieArgs.id, state.movie?.id)
                assertEquals(movieArgs.title, state.movie?.title)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `state movie should be null when movie args string is missing`() =
        runTest {
            initViewModel(movieJson = null)
            viewModel.state.test {
                val state = awaitItem()
                assertNull(state.movie)
                cancelAndIgnoreRemainingEvents()
            }
        }
}
