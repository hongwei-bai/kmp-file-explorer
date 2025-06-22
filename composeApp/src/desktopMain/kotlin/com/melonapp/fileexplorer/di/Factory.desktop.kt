package com.melonapp.fileexplorer.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.melonapp.fileexplorer.data.room.RoomAppDatabase
import kotlinx.coroutines.Dispatchers
import java.io.File

actual class Factory() {
    actual fun createRoomDatabase(): RoomAppDatabase {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
        return Room.databaseBuilder<RoomAppDatabase>(
            name = dbFile.absolutePath,
        ).fallbackToDestructiveMigrationOnDowngrade(
                dropAllTables = false
            )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
