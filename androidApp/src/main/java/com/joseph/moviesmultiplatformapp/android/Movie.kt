package com.joseph.moviesmultiplatformapp.android

import android.app.Application
import com.joseph.moviesmultiplatformapp.android.di.appModule
import com.joseph.moviesmultiplatformapp.di.fetchSharedModules
import org.koin.core.context.startKoin

class Movie : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + fetchSharedModules())
        }
    }
}