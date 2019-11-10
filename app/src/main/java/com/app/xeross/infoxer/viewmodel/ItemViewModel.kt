package com.app.xeross.infoxer.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.xeross.infoxer.DatabaseRepository
import com.app.xeross.infoxer.R
import com.app.xeross.infoxer.model.ItemModel
import com.app.xeross.infoxer.model.UserModel
import java.util.concurrent.Executors

class ItemViewModel(var context: Context) : ViewModel() {

    private val databaseRepository = DatabaseRepository(context)
    private val executor = Executors.newSingleThreadExecutor()
    private var item: MutableLiveData<ItemModel> = MutableLiveData()
    private var itemsListLiveData: MutableLiveData<List<ItemModel>> = MutableLiveData()
    private var itemsList: ArrayList<ItemModel> = ArrayList()

    fun createItemToFirebase(itemModel: ItemModel,
                             nameCategory: String,
                             userId: String) = executor.execute {
        databaseRepository.createItem(itemModel, nameCategory, userId)
                .addOnSuccessListener {
                }.addOnFailureListener {
                }
    }

    fun getItemToFirebase(nameCategory: String,
                          itemId: String,
                          userId: String): LiveData<ItemModel> {
        databaseRepository.getItem(userId, itemId, nameCategory)
                .addOnCompleteListener {
                    item.postValue(it.result!!.toObject(ItemModel::class.java))
                }
        return item
    }

    fun getItemsToFirebase(nameCategory: String, userId: String): LiveData<List<ItemModel>> {
        databaseRepository.getItems(userId, nameCategory).addOnSuccessListener { documents ->
            for (document in documents) {
                itemsList.add(document.toObject(ItemModel::class.java))
            }
            itemsListLiveData.postValue(itemsList)
        }
        return itemsListLiveData
    }
}