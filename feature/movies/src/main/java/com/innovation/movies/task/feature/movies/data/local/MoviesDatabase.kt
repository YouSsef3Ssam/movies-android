package com.innovation.movies.task.feature.movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.innovation.movies.task.feature.movies.data.local.converters.GenreIdsConverter
import com.innovation.movies.task.feature.movies.data.local.dao.MoviesDao
import com.innovation.movies.task.feature.movies.data.local.dao.RemoteKeysDao
import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.data.local.entities.MovieRemoteKey

@Database(
    entities = [MovieEntity::class, MovieRemoteKey::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(GenreIdsConverter::class)
internal abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}
