package com.innovation.movies.task.core.ui.extensions

import android.content.Context
import android.widget.Toast

fun Context.message(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
