package com.app.xeross.infoxer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.xeross.infoxer.viewmodel.CategoryViewModel
import java.lang.IllegalArgumentException
import java.util.concurrent.Executor


class ViewModelFactory(private var context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}