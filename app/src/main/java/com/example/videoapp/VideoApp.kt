package com.example.videoapp

import android.app.Application
import com.example.videoapp.data.di.apiModule
import com.example.videoapp.data.di.repositoryModule
import com.example.videoapp.domain.di.useCaseModule
import com.example.videoapp.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class VideoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(modules)
        }
    }

    private val modules = listOf(
        apiModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}