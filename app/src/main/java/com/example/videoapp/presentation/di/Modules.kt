package com.example.videoapp.presentation.di

import com.example.videoapp.presentation.video.VideoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { VideoListViewModel(get()) }
}