package com.innovation.movies.task.feature.movies.data.remote.source

import com.google.common.truth.Truth.assertThat
import com.innovation.movies.task.core.common.data.remote.dto.PaginatedResponse
import com.innovation.movies.task.feature.movies.common.PAGING_PAGE_SIZE
import com.innovation.movies.task.feature.movies.common.PAGING_STARTING_PAGE
import com.innovation.movies.task.feature.movies.common.di.moviesModuleTesting
import com.innovation.movies.task.feature.movies.data.remote.dto.MovieResponseDto
import com.innovation.movies.task.feature.movies.utils.TestingQualifier
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.core.qualifier.qualifier
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.junit5.KoinTestExtension

class RemoteMoviesSourceTest : KoinTest {
    @JvmField
    @RegisterExtension
    val koinTestExtension =
        KoinTestExtension.create {
            modules(moviesModuleTesting)
        }

    @AfterEach
    fun teardown() {
        println("clearing mocks...")
        clearAllMocks()
    }

    @Test
    fun `getMovies returns success when API call is successful`() = runTest {
        val source: MoviesSource = get(TestingQualifier.SUCCESS.qualifier)
        val response = source.getMovies(
            pageNumber = PAGING_STARTING_PAGE,
            pageSize = PAGING_PAGE_SIZE
        )
        assertThat(response).isInstanceOf(PaginatedResponse::class.java)
        assertThat(response.data?.first()).isInstanceOf(MovieResponseDto::class.java)
    }


    @Test
    fun `getMovies throws exception when API call fails`() = runTest {
        val source: MoviesSource = get(TestingQualifier.FAILURE.qualifier)
        val exception = runCatching {
            source.getMovies(
                pageNumber = PAGING_STARTING_PAGE,
                pageSize = PAGING_PAGE_SIZE
            )
        }.exceptionOrNull()
        assertThat(exception).isNotNull()
        assertThat(exception?.message).isNotNull()
    }

}

