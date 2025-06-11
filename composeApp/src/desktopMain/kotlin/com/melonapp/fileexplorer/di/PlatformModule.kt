package com.melonapp.fileexplorer.di

import com.melonapp.fileexplorer.data.room.RoomAppDatabaseBuilder
import com.melonapp.fileexplorer.data.room.RoomDatabaseHelper
import com.melonapp.fileexplorer.database.DatabaseDriverFactory
import com.melonapp.fileexplorer.database.DatabaseHelper
import org.koin.dsl.module

val platformModule = module {
    single { DatabaseDriverFactory() }
    single { DatabaseHelper(get()) }
    single { RoomAppDatabaseBuilder() }
    single { RoomDatabaseHelper(get()) }
}