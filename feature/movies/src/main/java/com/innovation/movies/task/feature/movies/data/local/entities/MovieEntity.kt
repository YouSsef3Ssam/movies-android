package com.innovation.movies.task.feature.movies.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.innovation.movies.task.feature.movies.common.MOVIES_TABLE_NAME

@Entity(tableName = MOVIES_TABLE_NAME)
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
)
