package com.melonapp.fileexplorer.di

import com.melonapp.fileexplorer.data.room.RoomAppDatabaseBuilder
import com.melonapp.fileexplorer.data.room.RoomDatabaseHelper
import org.koin.dsl.module

val platformModule = module {
    single { RoomAppDatabaseBuilder() }
    single { RoomDatabaseHelper(get()) }
}