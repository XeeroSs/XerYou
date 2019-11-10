package com.app.xeross.infoxer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.xeross.infoxer.viewmodel.ItemViewModel
import com.app.xeross.infoxer.viewmodel.UserViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory(private var context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(context) as T
        }
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            return ItemViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}