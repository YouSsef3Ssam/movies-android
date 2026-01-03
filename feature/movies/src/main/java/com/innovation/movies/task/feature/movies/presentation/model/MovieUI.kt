package com.innovation.movies.task.feature.movies.presentation.model

internal class MovieUI(
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
