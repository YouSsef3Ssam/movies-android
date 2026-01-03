package com.innovation.movies.task.feature.movies.presentation.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.innovation.movies.task.core.localization.R
import com.innovation.movies.task.core.ui.extensions.message
import com.innovation.movies.task.feature.movies.common.ITEM_APPEND_STATE_ERROR
import com.innovation.movies.task.feature.movies.common.ITEM_APPEND_STATE_LOADING
import com.innovation.movies.task.feature.movies.presentation.model.MovieUI
import com.innovation.movies.task.feature.movies.presentation.ui.movies.composables.AppendError
import com.innovation.movies.task.feature.movies.presentation.ui.movies.composables.AppendLoading
import com.innovation.movies.task.feature.movies.presentation.ui.movies.composables.LoadingFullScreen
import com.innovation.movies.task.feature.movies.presentation.ui.movies.composables.MovieItem
import com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesEvents
import com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesListNavigationActionCallbacks
import com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MoviesRoute(
    viewModel: MoviesViewModel = koinViewModel(),
    navigationCallbacks: (MoviesListNavigationActionCallbacks) -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val moviesPagingState = viewModel.moviesPagingFlow.collectAsLazyPagingItems()
    MoviesScreen(
        moviesPagingState = moviesPagingState,
        onEvents = viewModel::onEvent
    )
    if (state.callback != null) {
        viewModel.onEvent(event = MoviesEvents.NavigationCallbackHandled)
        navigationCallbacks(state.callback)
    }
}

@Composable
private fun MoviesScreen(
    moviesPagingState: LazyPagingItems<MovieUI>,
    onEvents: (MoviesEvents) -> Unit,
) {
    MoviesContent(
        moviesPagingState = moviesPagingState,
        onEvents = onEvents
    )
    HandleError(moviesPagingState = moviesPagingState)
}

@Composable
private fun MoviesContent(
    moviesPagingState: LazyPagingItems<MovieUI>,
    onEvents: (MoviesEvents) -> Unit,
) {
    val isRefreshing = moviesPagingState.loadState.refresh is LoadState.Loading
    val isEmpty = moviesPagingState.itemCount == 0
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (isRefreshing && isEmpty) {
            LoadingFullScreen(modifier = Modifier.fillMaxSize())
            return
        }
        LazyColumn(
            modifier = Modifier.weight(1f), // Take remaining space
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                count = moviesPagingState.itemCount,
                key = moviesPagingState.itemKey { it.id }
            ) { index ->
                moviesPagingState[index]?.let { movie ->
                    MovieItem(
                        movie = movie,
                        onClick = {
                            onEvents(MoviesEvents.MovieClicked(movie = movie))
                        }
                    )
                }
            }
            pagingAppendState(moviesPagingState = moviesPagingState)
        }
    }
}

@Composable
private fun HandleError(moviesPagingState: LazyPagingItems<MovieUI>) {
    val context = LocalContext.current
    val refreshError = moviesPagingState.loadState.refresh as? LoadState.Error
    LaunchedEffect(refreshError) {
        refreshError?.let {
            context.message(
                it.error.localizedMessage ?: context.getString(R.string.error_general_message)
            )
        }
    }
}


private fun LazyListScope.pagingAppendState(moviesPagingState: LazyPagingItems<MovieUI>) {
    when (val appendState = moviesPagingState.loadState.append) {
        is LoadState.Loading -> {
            item(key = ITEM_APPEND_STATE_LOADING) {
                AppendLoading(modifier = Modifier.fillParentMaxWidth())
            }
        }

        is LoadState.Error -> {
            item(key = ITEM_APPEND_STATE_ERROR) {
                AppendError(
                    message = appendState.error.localizedMessage
                        ?: stringResource(R.string.error_general_message),
                    modifier = Modifier.fillParentMaxWidth(),
                    onRetryClick = { moviesPagingState.retry() }
                )
            }
        }

        else -> Unit
    }
}
