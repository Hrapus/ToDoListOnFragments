package com.example.todolistonfragments.utilities

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val INIT_USER = "initUSer"
    private const val TYPE_DP = "typeDb"
    private const val NAME_PREF = "preference"

    private lateinit var mPreferences: SharedPreferences

    fun getPreferences(context: Context): SharedPreferences{
        mPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return mPreferences
    }

    fun setInitUser(init: Boolean){
        mPreferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setTypeDb(type: String){
        mPreferences.edit()
            .putString(TYPE_DP, type)
            .apply()
    }
    fun getInitUser(): Boolean{
        return mPreferences.getBoolean(INIT_USER, false)
    }

    fun getTypeDb(): String{
        return mPreferences.getString(TYPE_DP, TYPE_ROOM).toString()
    }
}