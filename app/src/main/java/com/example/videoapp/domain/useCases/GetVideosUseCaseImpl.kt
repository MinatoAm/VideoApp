package com.example.videoapp.domain.useCases

import com.example.videoapp.common.ActionResult
import com.example.videoapp.domain.model.Video
import com.example.videoapp.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow

class GetVideosUseCaseImpl(private val videoRepository: VideoRepository): GetVideosUseCase {
    override suspend fun invoke(): Flow<ActionResult<List<Video>>> = videoRepository.getVideos()
}