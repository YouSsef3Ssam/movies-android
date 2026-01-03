package com.innovation.movies.task.feature.movies.data.remote.source

import com.innovation.movies.task.core.common.data.remote.dto.PaginatedResponse
import com.innovation.movies.task.feature.movies.data.remote.dto.MovieResponseDto

internal interface MoviesSource {
    suspend fun getMovies(pageNumber: Int, pageSize: Int): PaginatedResponse<List<MovieResponseDto>>
}