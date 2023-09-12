package com.example.todolistonfragments.database.firebase

import androidx.lifecycle.LiveData
import com.example.todolistonfragments.database.DataBaseRepository
import com.example.todolistonfragments.models.AppNote
import com.example.todolistonfragments.utilities.EMAIL
import com.example.todolistonfragments.utilities.ID_FIREBASE
import com.example.todolistonfragments.utilities.NAME
import com.example.todolistonfragments.utilities.PASSWORD
import com.example.todolistonfragments.utilities.TEXT
import com.example.todolistonfragments.utilities.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRepository: DataBaseRepository {

    private val mAuth = FirebaseAuth.getInstance()

    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = mDatabaseReference.push().key.toString()
        val mapNote = hashMapOf<String,Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        mDatabaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{ showToast(it.message.toString()) }
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