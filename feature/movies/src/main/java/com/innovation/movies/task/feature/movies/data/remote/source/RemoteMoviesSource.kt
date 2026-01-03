package com.innovation.movies.task.feature.movies.data.remote.source

import com.innovation.movies.task.core.common.data.remote.dto.PaginatedResponse
import com.innovation.movies.task.core.network.extensions.performCall
import com.innovation.movies.task.feature.movies.common.GET_MOVIES_ENDPOINT
import com.innovation.movies.task.feature.movies.common.PAGE_NUMBER_QUERY_PARAMETER
import com.innovation.movies.task.feature.movies.common.PAGE_SIZE_QUERY_PARAMETER
import com.innovation.movies.task.feature.movies.data.remote.dto.MovieResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod

internal class RemoteMoviesSource(private val client: HttpClient) : MoviesSource {

    override suspend fun getMovies(
        pageNumber: Int,
        pageSize: Int
    ): PaginatedResponse<List<MovieResponseDto>> =
        client.performCall<Unit, PaginatedResponse<List<MovieResponseDto>>>(
            method = HttpMethod.Get,
            path = GET_MOVIES_ENDPOINT,
            requestBuilder = {
                parameter(PAGE_NUMBER_QUERY_PARAMETER, pageNumber)
                parameter(PAGE_SIZE_QUERY_PARAMETER, pageSize)
            }
        )
}