package com.melonapp.fileexplorer.di

import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoinIos() {
    startKoin {
        modules(
            module {
                single { Factory() }
            },
            commonModule
        )
    }
}