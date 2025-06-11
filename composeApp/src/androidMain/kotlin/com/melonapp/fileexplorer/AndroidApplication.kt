package com.melonapp.fileexplorer

import android.app.Application
import com.melonapp.fileexplorer.di.commonModule
import com.melonapp.fileexplorer.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AndroidApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                commonModule, // your shared module(s)
                platformModule(this@AndroidApplication) // android-specific DI stuff like NotificationManager
            )
            androidContext(this@AndroidApplication)
        }
    }
}