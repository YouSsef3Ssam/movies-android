package com.innovation.movies.task.core.network.extensions

import com.innovation.movies.task.core.network.BuildConfig
import com.innovation.movies.task.core.network.common.AUTH_HEADER_KEY
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

fun HttpRequestBuilder.configureRequestDefaults(
    method: HttpMethod,
    baseUrl: String,
    path: String,
    contentType: ContentType = ContentType.Application.Json,
    requestBuilder: HttpRequestBuilder.() -> Unit,
) {
    this.method = method
    url(baseUrl.plus(path))
    addDefaultsHeaders()
    headers {
        contentType(contentType)
    }
    requestBuilder()
}

private fun HttpRequestBuilder.addDefaultsHeaders() {
    headers {
        header(key = AUTH_HEADER_KEY, "Bearer ${BuildConfig.API_KEY}")
        accept(ContentType.Application.Json)
    }
}
