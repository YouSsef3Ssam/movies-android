package com.innovation.movies.task.core.navigation.entry.movieDetails.navTypes

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.innovation.movies.task.core.navigation.entry.movieDetails.args.MovieArgs
import kotlinx.serialization.json.Json

object MovieArgsNavType : NavType<MovieArgs?>(isNullableAllowed = true) {

    override fun get(bundle: Bundle, key: String): MovieArgs? {

        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): MovieArgs {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: MovieArgs?): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: MovieArgs?) {
        bundle.putString(key, Json.encodeToString(value))
    }
}