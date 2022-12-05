package com.example.videoapp.data.remote

import com.example.videoapp.data.remote.dto.VideoDto
import retrofit2.http.GET

interface VideoApi {

    @GET("/api/backgrounds/?group=video&category_id=1")
    suspend fun getVideos(): List<VideoDto>
}