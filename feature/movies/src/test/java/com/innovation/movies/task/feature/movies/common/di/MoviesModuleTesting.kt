package com.innovation.movies.task.feature.movies.common.di

import com.innovation.movies.task.feature.movies.data.remote.source.MoviesSource
import com.innovation.movies.task.feature.movies.data.remote.source.RemoteMoviesSource
import com.innovation.movies.task.feature.movies.utils.TestingQualifier
import com.innovation.movies.task.feature.movies.utils.ktorErrorClient
import com.innovation.movies.task.feature.movies.utils.ktorSuccessClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moviesModuleTesting =
    module {
        single<MoviesSource>(named(TestingQualifier.SUCCESS)) {
            RemoteMoviesSource(client = ktorSuccessClient)
        }
        single<MoviesSource>(named(TestingQualifier.FAILURE)) {
            RemoteMoviesSource(client = ktorErrorClient)
        }
    }
