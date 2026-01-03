package com.innovation.movies.task.core.network.common.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

private val networkClientModule =
    module {
        single<HttpClientEngine> { Android.create() }
        single<HttpClient> {
            provideKtorHttpClient(
                httpClientEngine = get(),
                json = get(),
            )
        }
    }

val networkModule = networkClientModule
