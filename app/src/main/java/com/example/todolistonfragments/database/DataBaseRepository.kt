package com.example.todolistonfragments.database

import androidx.lifecycle.LiveData
import com.example.todolistonfragments.models.AppNote

interface DataBaseRepository {
    val allNotes: LiveData<List<AppNote>>

    suspend fun insert(note: AppNote, onSuccess:()->Unit)
    suspend fun delete(note: AppNote, onSuccess:()->Unit)
}