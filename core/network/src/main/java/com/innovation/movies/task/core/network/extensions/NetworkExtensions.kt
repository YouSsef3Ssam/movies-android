package com.innovation.movies.task.core.network.extensions

import com.innovation.movies.task.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod

suspend inline fun <reified Request, reified Response> HttpClient.performCall(
    method: HttpMethod,
    baseUrl: String = BuildConfig.BASE_URL,
    path: String,
    body: Request? = null,
    contentType: ContentType = ContentType.Application.Json,
    noinline requestBuilder: HttpRequestBuilder.() -> Unit = { },
): Response {
    val response =
        request {
            configureRequestDefaults(
                method = method,
                baseUrl = baseUrl,
                path = path,
                contentType = contentType,
                requestBuilder = requestBuilder,
            )
            body?.let { setBody(body) }
        }
    return response.body<Response>()
}
