package com.joseph.moviesmultiplatformapp.data.utils

import com.joseph.moviesmultiplatformapp.di.fetchSharedModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(fetchSharedModules())
    }
}