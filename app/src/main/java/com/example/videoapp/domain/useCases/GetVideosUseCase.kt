package com.example.videoapp.domain.useCases

import com.example.videoapp.common.ActionResult
import com.example.videoapp.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface GetVideosUseCase {
    suspend operator fun invoke(): Flow<ActionResult<List<Video>>>
}