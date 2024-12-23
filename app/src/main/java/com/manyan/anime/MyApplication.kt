package com.manyan.anime

import android.app.Application
import com.manyan.anime.core.di.databaseModule
import com.manyan.anime.core.di.networkModule
import com.manyan.anime.core.di.repositoryModule
import com.manyan.anime.di.useCaseModule
import com.manyan.anime.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}