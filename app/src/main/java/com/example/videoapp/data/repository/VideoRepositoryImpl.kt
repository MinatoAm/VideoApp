package com.example.videoapp.data.repository

import com.example.videoapp.common.ActionResult
import com.example.videoapp.data.remote.VideoApi
import com.example.videoapp.data.remote.dto.toDomain
import com.example.videoapp.domain.model.Video
import com.example.videoapp.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class VideoRepositoryImpl(private val videoService: VideoApi) : VideoRepository {
    override fun getVideos(): Flow<ActionResult<List<Video>>> = flow {
        emit(ActionResult.Loading())
        try {
            val apiData = videoService.getVideos().map { it.toDomain() }
            emit(ActionResult.Success(apiData))
        } catch (e: HttpException) {
            emit(
                ActionResult.Error(
                    message = "Try again"
                )
            )
        } catch (e: IOException) {
            emit(
                ActionResult.Error(
                    message = "Check Internet Connection"
                )
            )
        }
    }
}