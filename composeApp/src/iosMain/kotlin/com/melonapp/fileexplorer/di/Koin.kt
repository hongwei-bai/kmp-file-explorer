package com.melonapp.fileexplorer.di

import org.koin.core.context.startKoin
import com.melonapp.fileexplorer.database.DatabaseDriverFactory
import org.koin.dsl.module

fun initKoinIos(driverFactory: DatabaseDriverFactory) {
    startKoin {
        modules(
            module {
                single { driverFactory }
            },
            commonModule
        )
    }
}