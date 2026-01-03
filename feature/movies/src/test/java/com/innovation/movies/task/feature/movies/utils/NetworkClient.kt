package com.innovation.movies.task.feature.movies.utils

import com.innovation.movies.task.feature.movies.common.GET_MOVIES_ENDPOINT
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private val responseHeaders = headersOf(HttpHeaders.ContentType, "application/json")

internal val ktorSuccessClient =
    HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.encodedPath) {
                    GET_MOVIES_ENDPOINT ->
                        respond(
                            GET_MOVIES_SUCCESS_RESPONSE,
                            HttpStatusCode.OK,
                            responseHeaders,
                        )

                    else -> error("Unhandled ${request.url.encodedPath}")
                }
            }
        }
        configureClient()
    }

internal val ktorErrorClient =
    HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.encodedPath) {
                    "/${GET_MOVIES_ENDPOINT}" ->
                        respond(
                            "",
                            HttpStatusCode.BadRequest,
                            responseHeaders,
                        )

                    else -> error("Unhandled ${request.url.encodedPath}")
                }
            }
        }
        configureClient()
    }

private fun HttpClientConfig<*>.configureClient() {
    expectSuccess = true
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            },
        )
    }
}
