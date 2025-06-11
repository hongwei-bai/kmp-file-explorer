package com.melonapp.fileexplorer.data.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(entities = [FileEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING", "KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomAppDatabase> {
    override fun initialize(): RoomAppDatabase
}