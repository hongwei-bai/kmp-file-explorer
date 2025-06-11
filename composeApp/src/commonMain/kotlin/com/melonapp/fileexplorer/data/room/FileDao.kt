package com.melonapp.fileexplorer.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update

@Dao
interface FileDao {

    @Insert
    suspend fun insert(file: FileEntity): Long

    @Update
    suspend fun update(file: FileEntity)

    @Query("DELETE FROM FileEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM FileEntity WHERE id = :id LIMIT 1")
    suspend fun getFileById(id: Long): FileEntity?

    @Query("SELECT * FROM FileEntity")
    suspend fun getAllFiles(): List<FileEntity>
}
