package com.example.videoapp.domain.repository

import com.example.videoapp.common.ActionResult
import com.example.videoapp.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideos(): Flow<ActionResult<List<Video>>>
}