package com.innovation.movies.task.core.common.extensions

import kotlinx.serialization.json.Json

inline fun <reified T> Json.encode(model: T): String =
    runCatching {
        encodeToString(model)
    }.getOrElse {
        return@getOrElse ""
    }

inline fun <reified T> Json.decode(string: String): T? =
    runCatching {
        decodeFromString<T>(string)
    }.getOrElse {
        return@getOrElse null
    }
