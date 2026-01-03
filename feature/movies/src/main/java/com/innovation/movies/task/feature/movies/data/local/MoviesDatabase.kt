package com.innovation.movies.task.feature.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.innovation.movies.task.feature.movies.data.local.dao.MoviesDao
import com.innovation.movies.task.feature.movies.data.local.dao.RemoteKeysDao
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.data.local.entities.MovieRemoteKey

@Database(
    entities = [MovieEntity::class, MovieRemoteKey::class],
    version = 1,
    exportSchema = false,
)
internal abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}
