package com.innovation.movies.task.feature.movies.domain.model

internal class DomainMovie(
    val id: String,
    val title: String,
    val posterImage: String,
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
