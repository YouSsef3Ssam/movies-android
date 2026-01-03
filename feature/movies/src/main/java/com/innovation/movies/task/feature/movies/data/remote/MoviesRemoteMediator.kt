package com.innovation.movies.task.feature.movies.data.remote

import android.net.http.HttpException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.innovation.movies.task.feature.movies.common.PAGING_STARTING_PAGE
import com.innovation.movies.task.feature.movies.data.local.MoviesDatabase
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.data.local.entities.MovieRemoteKey
import com.innovation.movies.task.feature.movies.data.mappers.toEntity
import com.innovation.movies.task.feature.movies.data.remote.source.MoviesSource
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
internal class MoviesRemoteMediator(
    private val remoteSource: MoviesSource,
    private val moviesDatabase: MoviesDatabase
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: PAGING_STARTING_PAGE
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }
        try {
            val apiResponse = remoteSource.getMovies(
                pageNumber = page,
                pageSize = state.config.pageSize
            )
            val movies = apiResponse
                .data
                .orEmpty()
                .map { it.toEntity() }

            val endOfPaginationReached = apiResponse.page == apiResponse.totalPages
            moviesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesDatabase.remoteKeysDao().clearRemoteKeys()
                    moviesDatabase.moviesDao().clearMovies()
                }
                val prevPage = if (page == PAGING_STARTING_PAGE) null else page - 1
                val nextPage = if (endOfPaginationReached) null else page + 1

                val keys = movies.map { movie ->
                    MovieRemoteKey(movieId = movie.id, prevPage = prevPage, nextPage = nextPage)
                }
                moviesDatabase.remoteKeysDao().insertAll(keys)
                moviesDatabase.moviesDao().insertAll(movies = movies)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): MovieRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                moviesDatabase.remoteKeysDao().remoteKeysById(movieId = movie.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): MovieRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                moviesDatabase.remoteKeysDao().remoteKeysById(movieId = movie.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { movie ->
                moviesDatabase.remoteKeysDao().remoteKeysById(movieId = movie.id)
            }
        }
    }
}