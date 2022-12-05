package com.example.videoapp.presentation.video

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoapp.common.ActionResult
import com.example.videoapp.domain.useCases.GetVideosUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class VideoListViewModel(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _state = mutableStateOf(VideoListState())
    val state: State<VideoListState> = _state

    init {
        getVideos()
    }

    private fun getVideos() = viewModelScope.launch {
        getVideosUseCase().onEach { result ->
            when (result) {
                is ActionResult.Success -> {
                    Log.d("Data checking", result.data.toString())
                    _state.value = VideoListState(videos = result.data ?: emptyList())
                }
                is ActionResult.Error -> {
                    _state.value = VideoListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is ActionResult.Loading -> {
                    _state.value = VideoListState(isLoading = true)
                }
            }
        }.launchIn(this)
    }
}