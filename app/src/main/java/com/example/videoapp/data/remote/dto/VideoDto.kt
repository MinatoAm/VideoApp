package com.example.videoapp.data.remote.dto


import com.example.videoapp.domain.model.Video
import com.google.gson.annotations.SerializedName

data class VideoDto(
    @SerializedName("file_url")
    val fileUrl: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("small_thumbnail_url")
    val smallThumbnailUrl: String?
)

fun VideoDto.toDomain() = Video(fileUrl = fileUrl?: "", id = id?: "", smallThumbnailUrl = smallThumbnailUrl?: "")