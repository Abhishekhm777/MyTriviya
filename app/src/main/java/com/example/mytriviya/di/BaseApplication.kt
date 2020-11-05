package com.example.mytriviya.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//Starting the Dipendency injection framework
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    appModule,
                    repositoryModule
                )
            )
        }
    }
}