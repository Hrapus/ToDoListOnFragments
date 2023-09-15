package com.example.todolistonfragments.database.firebase

import androidx.lifecycle.LiveData
import com.example.todolistonfragments.database.DataBaseRepository
import com.example.todolistonfragments.models.AppNote
import com.example.todolistonfragments.utilities.AUTH
import com.example.todolistonfragments.utilities.AppPreferences
import com.example.todolistonfragments.utilities.CURRENT_ID
import com.example.todolistonfragments.utilities.EMAIL
import com.example.todolistonfragments.utilities.ID_FIREBASE
import com.example.todolistonfragments.utilities.NAME
import com.example.todolistonfragments.utilities.PASSWORD
import com.example.todolistonfragments.utilities.REF_DATABASE
import com.example.todolistonfragments.utilities.TEXT
import com.example.todolistonfragments.utilities.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRepository : DataBaseRepository {
    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DATABASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDataBase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        if (AppPreferences.getInitUser()) {
            initRefs()
            onSuccess()
        } else {
            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    initRefs()
                    onSuccess() }
                .addOnFailureListener {
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                            initRefs()
                            onSuccess() }
                        .addOnFailureListener {
                            onFail(it.message.toString())
                        }
                }
        }
    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }


    override fun signOut() {
        AUTH.signOut()
    }
}