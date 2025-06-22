package com.melonapp.fileexplorer.data

import com.melonapp.fileexplorer.data.room.FileEntity
import com.melonapp.fileexplorer.data.room.RoomAppDatabase

class MyRepository(
    private val database: RoomAppDatabase
) {
    private val dao = database.fileDao()

    fun test() {
        println("test run!")
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