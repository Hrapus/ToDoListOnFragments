package com.example.todolistonfragments.screen.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todolistonfragments.database.room.AppRoomDatabase
import com.example.todolistonfragments.database.room.AppRoomRepository
import com.example.todolistonfragments.utilities.REPOSITORY
import com.example.todolistonfragments.utilities.TYPE_ROOM

class StartFragmentViewModel(application: Application): AndroidViewModel(application){
    private val mContext = application

    fun initDatabase(type: String, onSuccess:()->Unit){
        when(type){
            TYPE_ROOM -> {
               val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}
