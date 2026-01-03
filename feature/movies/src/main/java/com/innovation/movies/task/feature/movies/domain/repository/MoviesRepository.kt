package com.innovation.movies.task.feature.movies.domain.repository

import androidx.paging.PagingData
import com.innovation.movies.task.feature.movies.domain.model.DomainMovie
import kotlinx.coroutines.flow.Flow

internal interface MoviesRepository {
    fun getMovies(): Flow<PagingData<DomainMovie>>
}
