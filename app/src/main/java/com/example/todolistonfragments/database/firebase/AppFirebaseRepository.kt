package com.example.todolistonfragments.database.firebase

import androidx.lifecycle.LiveData
import com.example.todolistonfragments.database.DataBaseRepository
import com.example.todolistonfragments.models.AppNote
import com.example.todolistonfragments.utilities.EMAIL
import com.example.todolistonfragments.utilities.PASSWORD
import com.google.firebase.auth.FirebaseAuth

class AppFirebaseRepository: DataBaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    override val allNotes: LiveData<List<AppNote>>
        get() = TODO("Not yet implemented")

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDataBase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{
                        onFail(it.message.toString())
                    }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}