package com.innovation.movies.task.feature.movies.presentation.mapper

import com.innovation.movies.task.core.navigation.entry.movieDetails.args.MovieArgs
import com.innovation.movies.task.feature.movies.common.POSTER_BASE_URL
import com.innovation.movies.task.feature.movies.domain.model.DomainMovie
import com.innovation.movies.task.feature.movies.presentation.model.MovieUI

internal fun DomainMovie.toUI(): MovieUI =
    MovieUI(
        id = id,
        title = title,
        posterImage = "$POSTER_BASE_URL$posterPath",
        overview = overview,
        adult = adult,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originalLanguage = originalLanguage,
    )

internal fun MovieUI.toArgs(): MovieArgs =
    MovieArgs(
        id = id,
        title = title,
        posterImage = posterImage,
        overview = overview,
        adult = adult,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originalLanguage = originalLanguage,
    )

internal fun MovieArgs.toUI(): MovieUI =
    MovieUI(
        id = id,
        title = title,
        posterImage = posterImage,
        overview = overview,
        adult = adult,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originalLanguage = originalLanguage,
    )
