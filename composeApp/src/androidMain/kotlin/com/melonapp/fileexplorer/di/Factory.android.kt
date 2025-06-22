package com.melonapp.fileexplorer.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.melonapp.fileexplorer.data.room.RoomAppDatabase
import kotlinx.coroutines.Dispatchers

actual class Factory(
    private val applicationContext: Context,
) {
    actual fun createRoomDatabase(): RoomAppDatabase {
        val dbFile = applicationContext.getDatabasePath("my_room.db")
        return Room
            .databaseBuilder<RoomAppDatabase>(
                context = applicationContext,
                name = dbFile.absolutePath,
            ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
