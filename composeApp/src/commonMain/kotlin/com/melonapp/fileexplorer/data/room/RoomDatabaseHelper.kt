package com.melonapp.fileexplorer.data.room

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class RoomDatabaseHelper(
    builder: RoomAppDatabaseBuilder
) {
    val roomAppDatabase: RoomAppDatabase = builder.getBuilder()
//        .addMigrations(MIGRATIONS)
        .fallbackToDestructiveMigrationOnDowngrade(
            dropAllTables = false
        )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}