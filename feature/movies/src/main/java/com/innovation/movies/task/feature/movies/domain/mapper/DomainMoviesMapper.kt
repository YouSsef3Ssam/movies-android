package com.innovation.movies.task.feature.movies.domain.mapper

import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity
import com.innovation.movies.task.feature.movies.domain.model.DomainMovie

internal fun MovieEntity.toDomain(): DomainMovie = DomainMovie(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    adult = adult,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount,
    originalLanguage = originalLanguage
)
