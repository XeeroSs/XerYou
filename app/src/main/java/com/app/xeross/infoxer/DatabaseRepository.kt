package com.app.xeross.infoxer

import android.content.Context
import androidx.lifecycle.LiveData
import com.app.xeross.infoxer.utils.USERS_COLLECTION
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot

class DatabaseRepository(var context: Context) {

    fun getUsersCollection() =
            DatabaseFirebase.getInstance(context)!!.collection(USERS_COLLECTION)

    fun createUser(user: UserModel) =
            getUsersCollection().add(user)

    fun getUser(userId: String) =
            getUsersCollection().document(userId).get()

}