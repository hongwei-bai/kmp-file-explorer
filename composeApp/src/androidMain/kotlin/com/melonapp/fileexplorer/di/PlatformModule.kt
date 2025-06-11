package com.melonapp.fileexplorer.di

import org.koin.dsl.module
import com.melonapp.fileexplorer.database.DatabaseDriverFactory
import android.content.Context
import com.melonapp.fileexplorer.alarm.AlarmHelper
import com.melonapp.fileexplorer.data.room.RoomAppDatabaseBuilder
import com.melonapp.fileexplorer.data.room.RoomDatabaseHelper
import com.melonapp.fileexplorer.database.DatabaseHelper

fun platformModule(context: Context) = module {
    single { DatabaseDriverFactory(context) }
    single { DatabaseHelper(get()) }
    single<Context> { context.applicationContext }
    single { AlarmHelper(get()) }
    single { RoomAppDatabaseBuilder(context) }
    single { RoomDatabaseHelper(get()) }
}