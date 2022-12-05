package com.example.videoapp.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage


@Composable
@NonRestartableComposable
fun ImageLoader(
    url: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeHolderText: String? = null,
    @DrawableRes placeHolderRes: Int? = null,
) {
    val mod = modifier.fillMaxWidth()
    GlideImage(
        imageModel = {
            url
        },
        imageOptions = ImageOptions(
            contentScale = contentScale,
            contentDescription = contentDescription
        ),
        requestOptions = {
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
        },
        component = rememberImageComponent {
        },
        modifier = mod,
        failure = {
            if (placeHolderRes != null) {
                Icon(
                    modifier = Modifier
                        .heightIn(100.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    imageVector = ImageVector.vectorResource(id = placeHolderRes),
                    contentDescription = contentDescription,
                    tint = Color.Blue
                )
            } else if (!placeHolderText.isNullOrEmpty()) {
                Text(
                    text = placeHolderText,
                    color = Color.Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
            }
        },
    )
}

@Composable
@NonRestartableComposable
fun RoundedCornerImage(
    url: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val mod = modifier.clip(
        RoundedCornerShape(
            5.dp
        )
    )

    ImageLoader(
        url = url,
        modifier = mod,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}
