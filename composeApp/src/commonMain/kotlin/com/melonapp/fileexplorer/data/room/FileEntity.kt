package com.melonapp.fileexplorer.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val name: String,

    val path: String,

    @ColumnInfo(name = "date_create")
    val dateCreate: Long,

    val extension: String
)
