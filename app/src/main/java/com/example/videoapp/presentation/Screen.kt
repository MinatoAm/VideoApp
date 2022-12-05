package com.example.videoapp.presentation

sealed class Screen(val route: String) {
    object VideoListScreen: Screen("video_list_screen")
}
