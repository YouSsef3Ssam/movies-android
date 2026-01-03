package com.innovation.movies.task.feature.movies.common.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.innovation.movies.task.core.common.data.DispatcherQualifier
import com.innovation.movies.task.core.common.di.commonModule
import com.innovation.movies.task.core.network.common.di.networkModule
import com.innovation.movies.task.feature.movies.common.PAGING_PAGE_SIZE
import com.innovation.movies.task.feature.movies.common.PAGING_PREFETCH_DISTANCE
import com.innovation.movies.task.feature.movies.data.local.MoviesDatabase
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.data.remote.MoviesRemoteMediator
import com.innovation.movies.task.feature.movies.data.remote.source.MoviesSource
import com.innovation.movies.task.feature.movies.data.remote.source.RemoteMoviesSource
import com.innovation.movies.task.feature.movies.data.repository.MoviesRepositoryImpl
import com.innovation.movies.task.feature.movies.domain.repository.MoviesRepository
import com.innovation.movies.task.feature.movies.presentation.ui.movieDetails.manipulator.MovieDetailsViewModel
import com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

private val dataSources = module {
    single<MoviesSource> {
        RemoteMoviesSource(client = get())
    }
}


@OptIn(ExperimentalPagingApi::class)
private val pager = module {
    single<Pager<Int, MovieEntity>> {
        val moviesDatabase = get<MoviesDatabase>()
        Pager(
            config = PagingConfig(
                initialLoadSize = PAGING_PAGE_SIZE,
                pageSize = PAGING_PAGE_SIZE,
                prefetchDistance = PAGING_PREFETCH_DISTANCE,
                enablePlaceholders = true
            ),
            remoteMediator = MoviesRemoteMediator(
                remoteSource = get(),
                moviesDatabase = moviesDatabase
            ),
            pagingSourceFactory = {
                moviesDatabase.moviesDao().pagingSource()
            }
        )
    }
}

private val repositories = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            pager = get(),
            ioDispatcher = get(DispatcherQualifier.IO.qualifier)
        )
    }
}

private val viewModels = module {
    viewModelOf(::MoviesViewModel)
    viewModelOf(::MovieDetailsViewModel)
}

val moviesModule =
    commonModule + networkModule + databaseModule + dataSources + pager + repositories + viewModels