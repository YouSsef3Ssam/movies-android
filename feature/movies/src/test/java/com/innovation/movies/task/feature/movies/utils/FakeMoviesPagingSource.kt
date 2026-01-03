package com.innovation.movies.task.feature.movies.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity

internal class FakeMoviesPagingSource : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return LoadResult.Page(
            data = listOf(mockedMovieEntity),
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? = null
}