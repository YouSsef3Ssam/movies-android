package com.innovation.movies.task.core.ui.sharedcomponent.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import com.innovation.movies.task.core.ui.R as CoreUIR

@Composable
fun InnovationRemoteImage(
    modifier: Modifier = Modifier,
    image: Any,
    @DrawableRes fallBackDrawable: Int? = CoreUIR.drawable.ic_image_error,
    @DrawableRes placeholderDrawable: Int? = CoreUIR.drawable.ic_image_placeholder,
    contentScale: ContentScale = ContentScale.FillWidth,
    imageLoader: ImageLoader,
) {
    AsyncImage(
        model = image,
        placeholder = placeholderDrawable?.let { painterResource(id = it) },
        error = fallBackDrawable?.let { painterResource(id = it) },
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
        imageLoader = imageLoader,
    )
}

@Composable
fun InnovationRemoteImage(
    modifier: Modifier = Modifier,
    image: Any,
    @DrawableRes fallBackDrawable: Int? = CoreUIR.drawable.ic_image_error,
    @DrawableRes placeholderDrawable: Int? = CoreUIR.drawable.ic_image_placeholder,
    contentScale: ContentScale = ContentScale.FillWidth,
) {
    InnovationRemoteImage(
        modifier = modifier,
        image = image,
        fallBackDrawable = fallBackDrawable,
        placeholderDrawable = placeholderDrawable,
        contentScale = contentScale,
        imageLoader = LocalContext.current.imageLoader,
    )
}

@Composable
fun InnovationRemoteRoundedImage(
    modifier: Modifier = Modifier,
    image: Any,
    shape: RoundedCornerShape = CircleShape,
    borderSize: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = Color.Transparent,
    @DrawableRes fallBackDrawable: Int? = CoreUIR.drawable.ic_image_error,
    @DrawableRes placeholderDrawable: Int? = CoreUIR.drawable.ic_image_placeholder,
    contentScale: ContentScale = ContentScale.FillWidth,
) {
    Box(
        modifier =
            modifier
                .border(BorderStroke(width = borderSize, color = borderColor), shape = shape)
                .clip(shape = shape)
                .background(backgroundColor),
    ) {
        InnovationRemoteImage(
            modifier = Modifier.padding(contentPadding),
            image = image,
            fallBackDrawable = fallBackDrawable,
            placeholderDrawable = placeholderDrawable,
            contentScale = contentScale,
        )
    }
}
