package com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.innovation.movies.task.feature.movies.common.PAGING_PAGE_SIZE
import com.innovation.movies.task.feature.movies.domain.mapper.toDomain
import com.innovation.movies.task.feature.movies.domain.repository.MoviesRepository
import com.innovation.movies.task.feature.movies.presentation.mapper.toUI
import com.innovation.movies.task.feature.movies.presentation.model.MovieUI
import com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.utils.FailingMoviesRemoteMediator
import com.innovation.movies.task.feature.movies.utils.FakeMoviesPagingSource
import com.innovation.movies.task.feature.movies.utils.IdBasedDiffCallback
import com.innovation.movies.task.feature.movies.utils.NoopListCallback
import com.innovation.movies.task.feature.movies.utils.mockedMovieEntity
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertInstanceOf
import org.junit.jupiter.api.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class MoviesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val repository: MoviesRepository = mockk()
    private lateinit var viewModel: MoviesViewModel

    private val differ = AsyncPagingDataDiffer(
        diffCallback = IdBasedDiffCallback<MovieUI> { it.id },
        updateCallback = NoopListCallback,
        workerDispatcher = Dispatchers.Main
    )

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { repository.getMovies() } returns emptyFlow()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun initViewModel() {
        viewModel = MoviesViewModel(repository)
    }

    /**
     * State & Event Tests
     */

    @Test
    fun `initial state should have null callback`() = runTest {
        initViewModel()
        assertNull(viewModel.state.value.callback)
        coVerify(exactly = 1) { repository.getMovies() }
    }

    @Test
    fun `on MovieClicked event should update state with NavigateToDetails callback`() = runTest {
        initViewModel()
        val movie = mockedMovieEntity.toDomain().toUI()
        viewModel.onEvent(MoviesEvents.MovieClicked(movie = movie))

        val state = viewModel.state.value
        val callback = state.callback

        assertTrue(callback is MoviesListNavigationActionCallbacks.NavigateToDetails)
        assertEquals(movie.id, callback.movie?.id)
        coVerify(exactly = 1) { repository.getMovies() }
    }

    /**
     * Paging Tests
     */
    @Test
    fun `moviesPagingFlow should emit correctly mapped UI models from repository`() = runTest {
        every { repository.getMovies() } returns flowOf(PagingData.from(listOf(mockedMovieEntity.toDomain())))
        initViewModel()

        val job = launch {
            viewModel.moviesPagingFlow.collect { differ.submitData(it) }
        }
        advanceUntilIdle()

        assertEquals(1, differ.itemCount)
        val item = differ.getItem(0)!!
        assertInstanceOf<MovieUI>(item)
        assertEquals(mockedMovieEntity.id, item.id)
        assertEquals(mockedMovieEntity.title, item.title)
        coVerify(exactly = 1) { repository.getMovies() }
        job.cancel()
    }

    @Test
    fun `paging flow should handle repository errors gracefully without crashing`() = runTest {
        every { repository.getMovies() } returns flow { throw RuntimeException("Stream error") }
        initViewModel()

        val job = launch {
            viewModel.moviesPagingFlow.collect { differ.submitData(it) }
        }
        advanceUntilIdle()
        assertEquals(0, differ.itemCount)
        coVerify(exactly = 1) { repository.getMovies() }
        job.cancel()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `remote mediator failure should be reflected in differ load state`() = runTest {
        val pager = Pager(
            config = PagingConfig(PAGING_PAGE_SIZE),
            remoteMediator = FailingMoviesRemoteMediator(),
            pagingSourceFactory = { FakeMoviesPagingSource() }
        )

        every { repository.getMovies() } returns pager.flow.map { data -> data.map { it.toDomain() } }
        initViewModel()

        val job = launch {
            viewModel.moviesPagingFlow.collect { differ.submitData(it) }
        }
        advanceUntilIdle()

        val loadState = differ.loadStateFlow.first()
        val refreshState = loadState.refresh
        assertTrue(refreshState is LoadState.Error)
        val errorState = loadState.refresh as LoadState.Error
        assertEquals(
            FailingMoviesRemoteMediator.ERROR_MESSAGE,
            errorState.error.message
        )
        coVerify(exactly = 1) { repository.getMovies() }
        job.cancel()
    }
}

