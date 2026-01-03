package com.innovation.movies.task.feature.movies.domain.model


internal class DomainMovie(
    val id: String,
    val title: String,
    val posterPath: String?,
    val overview: String,
    val adult: Boolean,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originalLanguage: String
)
