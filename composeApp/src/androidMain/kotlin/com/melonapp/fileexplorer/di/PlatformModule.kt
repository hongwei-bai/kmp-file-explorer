package com.melonapp.fileexplorer.di

import org.koin.dsl.module
import android.content.Context
import com.melonapp.fileexplorer.alarm.AlarmHelper
import com.melonapp.fileexplorer.data.room.RoomAppDatabaseBuilder
import com.melonapp.fileexplorer.data.room.RoomDatabaseHelper

fun platformModule(context: Context) = module {
    single<Context> { context.applicationContext }
    single { AlarmHelper(get()) }
    single { RoomAppDatabaseBuilder(context) }
    single { RoomDatabaseHelper(get()) }
}