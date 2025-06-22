package com.melonapp.fileexplorer.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.melonapp.fileexplorer.data.room.RoomAppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class Factory {
    actual fun createRoomDatabase(): RoomAppDatabase {
        val dbFile = "${fileDirectory()}/room_db.db"
        return Room
            .databaseBuilder<RoomAppDatabase>(
                name = dbFile,
            ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun fileDirectory(): String {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory).path!!
    }
}
