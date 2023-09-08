package com.example.todolistonfragments.screen.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistonfragments.models.AppNote
import com.example.todolistonfragments.utilities.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(application: Application): AndroidViewModel(application) {
    fun insert(note: AppNote, onSuccess:()-> Unit){
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.insert(note){
                onSuccess()
            }
        }
    }
}