package com.innovation.movies.task.core.network.common.di

import com.innovation.movies.task.core.network.BuildConfig
import com.pluto.plugins.network.interceptors.ktor.PlutoKtorInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

internal fun provideKtorHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
): HttpClient =
    HttpClient(httpClientEngine) {
        expectSuccess = true
        configureJson(json = json)
        configureLogging()
    }

private fun HttpClientConfig<*>.configureJson(json: Json) {
    install(ContentNegotiation) {
        json(json)
    }
}

private fun HttpClientConfig<*>.configureLogging() {
    if (BuildConfig.DEBUG) {
        Logging {
            level = LogLevel.ALL
            logger =
                object : Logger {
                    override fun log(message: String) {
                        Timber.d("Network Logger-> $message")
                    }
                }
        }
        install(PlutoKtorInterceptor)
    }
}
