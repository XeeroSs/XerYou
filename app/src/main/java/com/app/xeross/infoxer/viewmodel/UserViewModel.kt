package com.app.xeross.infoxer.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.xeross.infoxer.DatabaseRepository
import com.app.xeross.infoxer.R
import com.app.xeross.infoxer.model.UserModel
import java.util.concurrent.Executors

class UserViewModel(var context: Context) : ViewModel() {

    private val databaseRepository = DatabaseRepository(context)
    private val executor = Executors.newSingleThreadExecutor()
    private var user: MutableLiveData<UserModel> = MutableLiveData()

    fun createItemToFirebase(user: UserModel) = executor.execute {
        databaseRepository.createUser(user).addOnSuccessListener {
            Toast.makeText(context, "Welcome ${user.userName} !", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(context, context.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        }
    }

    fun getUserToFirebase(userId: String): LiveData<UserModel> {
        databaseRepository.getUser(userId).addOnCompleteListener {
            user.postValue(it.result!!.toObject(UserModel::class.java))
        }
        return user
    }

}
