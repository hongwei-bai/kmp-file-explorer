package com.melonapp.fileexplorer.di

import com.melonapp.fileexplorer.data.MyRepository
import com.melonapp.fileexplorer.data.room.AppDatabaseConstructor
import com.melonapp.fileexplorer.data.room.RoomAppDatabase
import com.melonapp.fileexplorer.data.room.RoomAppDatabaseBuilder
import com.melonapp.fileexplorer.data.room.RoomDatabaseHelper
import org.koin.dsl.module
import com.melonapp.fileexplorer.database.DatabaseHelper
import com.melonapp.fileexplorer.domain.SomeUseCase
import com.melonapp.fileexplorer.nav.NavViewModel
import com.melonapp.fileexplorer.ui.CounterViewModel

val commonModule = module {
    single { DatabaseHelper(get()) }
    single { MyRepository(get(), get()) }
    single { SomeUseCase(get()) }
    single { CounterViewModel(get()) }

    factory { NavViewModel() }

    single<RoomAppDatabase> { AppDatabaseConstructor.initialize() }
//    single { get<RoomAppDatabase>().fileDao() }
    single { RoomDatabaseHelper(get()) }
}