package com.melonapp.fileexplorer.data.room

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class RoomAppDatabaseBuilder() {
    actual fun getBuilder(): RoomDatabase.Builder<RoomAppDatabase> {
        return getDatabaseBuilder()
    }
}

fun getDatabaseBuilder(): RoomDatabase.Builder<RoomAppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<RoomAppDatabase>(
        name = dbFile.absolutePath,
    )
}