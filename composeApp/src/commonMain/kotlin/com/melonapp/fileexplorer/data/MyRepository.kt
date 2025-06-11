package com.melonapp.fileexplorer.data

import com.melonapp.fileexplorer.data.room.FileEntity
import com.melonapp.fileexplorer.data.room.RoomDatabaseHelper
import com.melonapp.fileexplorer.database.DatabaseHelper

class MyRepository(
    private val dbHelper: DatabaseHelper,
    private val roomDatabaseHelper: RoomDatabaseHelper
) {
    private val dao = roomDatabaseHelper.roomAppDatabase.fileDao()

    fun test() {
        println("test run!")
    }

    fun insertUser(name: String, age: Int) {
        dbHelper.insert(1, name, age, "test student")
    }

    fun clearAllUsers() {
        dbHelper.clearAll()
    }

    fun getUserName(): String? {
        return dbHelper.getRecordById(1)?.name
    }

    suspend fun insertFileViaRoom() {
        dao.insert(
            file = FileEntity(
                id = 1,
                name = "sample_file",
                path = "C:\\",
                dateCreate = 10000000L,
                extension = "txt"
            )
        )
    }

    suspend fun getFiles(): List<FileEntity> {
        return dao.getAllFiles()
    }

    suspend fun clearFiles() {
        dao.deleteAll()
    }
}