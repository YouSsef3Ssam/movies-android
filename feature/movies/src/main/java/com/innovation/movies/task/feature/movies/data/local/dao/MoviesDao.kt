package com.innovation.movies.task.feature.movies.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.innovation.movies.task.feature.movies.common.MOVIES_TABLE_NAME
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity

@Dao
internal interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM $MOVIES_TABLE_NAME")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM $MOVIES_TABLE_NAME")
    suspend fun clearMovies()
}
