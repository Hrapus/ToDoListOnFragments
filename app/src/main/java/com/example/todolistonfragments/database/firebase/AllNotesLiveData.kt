package com.example.todolistonfragments.database.firebase

import androidx.lifecycle.LiveData
import com.example.todolistonfragments.models.AppNote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotesLiveData : LiveData<List<AppNote>>() {
    private val mAuth = FirebaseAuth.getInstance()

    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener{

        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNote::class.java)?: AppNote()
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }

    override fun onActive() {
        mDatabaseReference.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        mDatabaseReference.removeEventListener(listener)
        super.onInactive()
    }
}