package com.example.todolistonfragments.utilities

import com.example.todolistonfragments.MainActivity
import com.example.todolistonfragments.database.DataBaseRepository

lateinit var REPOSITORY: DataBaseRepository
lateinit var APP_ACTIVITY: MainActivity
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
lateinit var EMAIL: String
lateinit var PASSWORD: String
const val TYPE_FIREBASE = "type_firebase"