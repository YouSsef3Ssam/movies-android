package com.innovation.movies.task.feature.movies.utils

import com.innovation.movies.task.feature.movies.data.local.entities.MovieEntity

internal val mockedMovieEntity =
    MovieEntity(
        id = "1",
        title = "Interstellar",
        posterPath = "poster",
        overview = "overview",
        voteAverage = 9.0,
        voteCount = 1,
        releaseDate = "date",
        adult = false,
        originalLanguage = "en",
    )
