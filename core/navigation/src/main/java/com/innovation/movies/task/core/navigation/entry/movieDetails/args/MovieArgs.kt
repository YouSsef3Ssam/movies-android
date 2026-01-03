package com.innovation.movies.task.core.navigation.entry.movieDetails.args

import kotlinx.serialization.Serializable

@Serializable
class MovieArgs(
    val id: String,
    val title: String,
    val posterImage: String,
    val overview: String,
    val adult: Boolean,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originalLanguage: String
)
