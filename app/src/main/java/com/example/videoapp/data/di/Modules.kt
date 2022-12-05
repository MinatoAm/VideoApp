package com.example.videoapp.data.di

import com.example.videoapp.common.Constants.BASE_URL
import com.example.videoapp.data.remote.VideoApi
import com.example.videoapp.data.repository.VideoRepositoryImpl
import com.example.videoapp.domain.repository.VideoRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<VideoApi> { get<Retrofit>().create(VideoApi::class.java) }
}

val repositoryModule = module {
    single<VideoRepository> { VideoRepositoryImpl(get()) }
}