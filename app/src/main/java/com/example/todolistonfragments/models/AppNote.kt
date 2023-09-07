package com.example.todolistonfragments.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class AppNote(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo val name: String = "",
    @ColumnInfo val text: String = ""
)