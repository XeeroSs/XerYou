package com.app.xeross.infoxer

import android.content.Context
import com.app.xeross.infoxer.model.ItemModel
import com.app.xeross.infoxer.model.UserModel
import com.app.xeross.infoxer.utils.USERS_COLLECTION
import com.google.firebase.firestore.Exclude

class DatabaseRepository(var context: Context) {

    fun getUsersCollection() =
            DatabaseFirebase.getInstance(context)!!.collection(USERS_COLLECTION)

    fun createUser(user: UserModel) =
            getUsersCollection().add(user)

    fun getUser(userId: String) =
            getUsersCollection().document(userId).get()

    fun getItem(userId: String, itemId: String, nameCategory: String) =
            getUsersCollection().document(userId).collection(nameCategory).document(itemId).get()

    fun getItems(userId: String, nameCategory: String) =
            getUsersCollection().document(userId).collection(nameCategory).get()

    @Exclude
    fun createItem(itemModel: ItemModel, nameCategory: String, userId: String) = getUsersCollection().document(userId).collection(nameCategory).add(itemModel)


}