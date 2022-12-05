package com.example.videoapp.domain.di

import com.example.videoapp.domain.useCases.GetVideosUseCase
import com.example.videoapp.domain.useCases.GetVideosUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetVideosUseCase> {GetVideosUseCaseImpl(get())}
}