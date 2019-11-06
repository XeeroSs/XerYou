package com.app.xeross.infoxer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.app.xeross.infoxer.utils.RC_SIGN_IN
import com.app.xeross.infoxer.viewmodel.CategoryViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        configureViewModel()
        configureUI()
    }

    private fun configureUI() {
        loginActivity_ButtonLogin.setOnClickListener {
            loginWithGoogle()
        }
    }

    private fun loginWithGoogle() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .build(), RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val user = UserModel(currentUser!!.displayName!!, currentUser.uid, currentUser.email!!)
                viewModel.createItemToFirebase(user = user)
            }
        }
    }

    private fun configureViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CategoryViewModel::class.java)
    }
}
