package com.innovation.movies.task.feature.movies.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.domain.mapper.toDomain
import com.innovation.movies.task.feature.movies.domain.model.DomainMovie
import com.innovation.movies.task.feature.movies.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class MoviesRepositoryImpl(
    private val pager: Pager<Int, MovieEntity>,
    private val ioDispatcher: CoroutineDispatcher,
) : MoviesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies(): Flow<PagingData<DomainMovie>> =
        pager
            .flow
            .map { paging ->
                paging.map { it.toDomain() }
            }
            .flowOn(ioDispatcher)
}
