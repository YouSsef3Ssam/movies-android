package com.innovation.movies.task.feature.movies.utils

import androidx.recyclerview.widget.DiffUtil

internal class IdBasedDiffCallback<T>(
    private val idSelector: (T) -> Any,
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T & Any,
        newItem: T & Any,
    ): Boolean = idSelector(oldItem) == idSelector(newItem)

    override fun areContentsTheSame(
        oldItem: T & Any,
        newItem: T & Any,
    ): Boolean = oldItem == newItem
}
