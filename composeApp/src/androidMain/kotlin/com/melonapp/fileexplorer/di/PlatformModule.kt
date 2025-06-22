package com.melonapp.fileexplorer.di

import org.koin.dsl.module
import android.content.Context
import com.melonapp.fileexplorer.alarm.AlarmHelper

fun platformModule(context: Context) = module {
    single<Context> { context.applicationContext }
    single { AlarmHelper(get()) }
    single { Factory(context.applicationContext) }
}