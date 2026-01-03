package com.innovation.movies.task.feature.movies.data.repository

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.innovation.movies.task.feature.movies.domain.model.DomainMovie
import com.innovation.movies.task.feature.movies.utils.FakeMoviesPagingSource
import com.innovation.movies.task.feature.movies.utils.IdBasedDiffCallback
import com.innovation.movies.task.feature.movies.utils.NoopListCallback
import com.innovation.movies.task.feature.movies.utils.mockedMovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import org.junit.jupiter.api.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
internal class MoviesRepositoryTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: MoviesRepositoryImpl

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMovies should emit correctly mapped DomainMovies from PagingSource`() =
        runTest {
            val pager =
                Pager(
                    config = PagingConfig(pageSize = 10),
                    pagingSourceFactory = { FakeMoviesPagingSource() },
                )
            repository =
                MoviesRepositoryImpl(
                    pager = pager,
                    ioDispatcher = testDispatcher,
                )
            val differ =
                AsyncPagingDataDiffer(
                    diffCallback = IdBasedDiffCallback<DomainMovie> { it.id },
                    updateCallback = NoopListCallback,
                    workerDispatcher = Dispatchers.Main,
                )
            val job =
                launch {
                    repository.getMovies().collect {
                        differ.submitData(it)
                    }
                }
            advanceUntilIdle()

            assertEquals(1, differ.itemCount)
            val domainMovie = differ.getItem(0)
            assertNotNull(domainMovie)
            assertInstanceOf<DomainMovie>(domainMovie)
            assertEquals(mockedMovieEntity.id, domainMovie.id)
            assertEquals(mockedMovieEntity.title, domainMovie.title)
            assertEquals(mockedMovieEntity.posterPath, domainMovie.posterPath)
            assertEquals(mockedMovieEntity.overview, domainMovie.overview)
            assertEquals(mockedMovieEntity.voteAverage, domainMovie.voteAverage)
            assertEquals(mockedMovieEntity.voteCount, domainMovie.voteCount)
            assertEquals(mockedMovieEntity.originalLanguage, domainMovie.originalLanguage)
            assertEquals(mockedMovieEntity.releaseDate, domainMovie.releaseDate)
            assertEquals(mockedMovieEntity.adult, domainMovie.adult)
            job.cancel()
        }
}
