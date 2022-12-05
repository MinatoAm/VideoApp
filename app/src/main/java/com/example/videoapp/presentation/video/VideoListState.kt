package com.example.videoapp.presentation.video

import com.example.videoapp.domain.model.Video

data class VideoListState(
    val isLoading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val error: String = ""
)
