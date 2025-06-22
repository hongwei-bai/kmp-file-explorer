package com.melonapp.fileexplorer.di

import com.melonapp.fileexplorer.data.MyRepository
import com.melonapp.fileexplorer.data.room.AppDatabaseConstructor
import com.melonapp.fileexplorer.data.room.RoomAppDatabase
import org.koin.dsl.module
import com.melonapp.fileexplorer.nav.NavViewModel
import com.melonapp.fileexplorer.ui.CounterViewModel

val commonModule = module {
    single { CounterViewModel(get()) }

    factory { NavViewModel() }

    single<RoomAppDatabase> { AppDatabaseConstructor.initialize() }
//    single { get<RoomAppDatabase>().fileDao() }
    single { MyRepository(get<Factory>().createRoomDatabase()) }
}