package com.melonapp.fileexplorer.di

import org.koin.dsl.module

val platformModule = module {
    single { Factory() }
}