package com.innovation.movies.task.feature.movies.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.innovation.movies.task.feature.movies.common.MOVIES_TABLE_NAME
import com.innovation.movies.task.feature.movies.data.local.converters.GenreIdsConverter

@Entity(tableName = MOVIES_TABLE_NAME)
@TypeConverters(GenreIdsConverter::class)
internal class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val posterPath: String,
    val overview: String,
    val adult: Boolean,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originalLanguage: String,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalTitle: String,
    val popularity: Double,
    val video: Boolean,
)
