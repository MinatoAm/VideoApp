package com.example.videoapp.presentation.video.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.videoapp.R
import com.example.videoapp.domain.model.Video
import com.example.videoapp.presentation.ui.components.RoundedCornerImage

@Composable
fun VideoListItem(
    modifier: Modifier,
    videos: List<Video>,
    checkedItemId: String,
    onItemClick: (Video) -> Unit,
) {
    LazyRow(
        modifier = modifier.padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(videos) { item ->
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            ) {
                RoundedCornerImage(
                    url = item.smallThumbnailUrl,
                    modifier = Modifier
                        .height(70.dp)
                        .width(70.dp)
                        .clickable { onItemClick.invoke(item) }
                        .alpha(
                            if (checkedItemId == item.id) 10f else 0.5f
                        )
                        .border(
                            if (checkedItemId == item.id) 2.dp else 0.dp,
                            color = colorResource(id = R.color.video_list_item_border),
                            shape = RoundedCornerShape(5.dp)
                        )
                )
            }
        }
    }
}