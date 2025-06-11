package com.melonapp.fileexplorer.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class RoomAppDatabaseBuilder(val appContext: Context) {
    actual fun getBuilder(): RoomDatabase.Builder<RoomAppDatabase> {
        return getDatabaseBuilder(appContext)
    }
}

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<RoomAppDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<RoomAppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}