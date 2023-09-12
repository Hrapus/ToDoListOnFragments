package com.example.todolistonfragments.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class AppNote(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    @ColumnInfo val name: String = "",
    @ColumnInfo val text: String = "",
    val idFirebase: String = ""
): Serializable