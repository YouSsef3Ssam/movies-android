package com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.utils

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity

@OptIn(ExperimentalPagingApi::class)
internal class FailingMoviesRemoteMediator : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return MediatorResult.Error(
            RuntimeException(ERROR_MESSAGE)
        )
    }

    companion object {
        const val ERROR_MESSAGE = "Network error from mediator"
    }
}

