package com.innovation.movies.task.feature.movies.utils

import androidx.recyclerview.widget.ListUpdateCallback

internal object NoopListCallback : ListUpdateCallback {
    override fun onInserted(
        position: Int,
        count: Int,
    ) = Unit

    override fun onRemoved(
        position: Int,
        count: Int,
    ) = Unit

    override fun onMoved(
        fromPosition: Int,
        toPosition: Int,
    ) = Unit

    override fun onChanged(
        position: Int,
        count: Int,
        payload: Any?,
    ) = Unit
}
