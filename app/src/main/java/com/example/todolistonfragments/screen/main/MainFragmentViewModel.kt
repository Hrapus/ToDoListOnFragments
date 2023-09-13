package com.example.todolistonfragments.screen.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todolistonfragments.utilities.REPOSITORY

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes
    fun signOut(){
        REPOSITORY.signOut()
    }
}