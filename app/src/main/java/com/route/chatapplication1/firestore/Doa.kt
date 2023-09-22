package com.route.chatapplication1.firestore

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.route.chatapplication1.firestore.model.User

object Doa {
    private fun getCollection(): CollectionReference {
        var fireStore=Firebase.firestore
        return fireStore.collection("users")
    }
    fun createUser(user:User,onCompleteListener: OnCompleteListener<Void?>){
        var documentRef=getCollection().document(user.id?:"")
        documentRef.set(user)
            .addOnCompleteListener(onCompleteListener)
    }

    fun getUser(uid: String?,onCompleteListener: OnCompleteListener<DocumentSnapshot?>) {
        getCollection()
            .document(uid ?: "")
            .get()
            .addOnCompleteListener(onCompleteListener)

    }
}