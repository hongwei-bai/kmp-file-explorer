package com.melonapp.fileexplorer.data.room

import androidx.room.RoomDatabase

expect class RoomAppDatabaseBuilder {
    fun getBuilder(): RoomDatabase.Builder<RoomAppDatabase>
}