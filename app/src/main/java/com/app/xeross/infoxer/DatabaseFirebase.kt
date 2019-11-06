package com.app.xeross.infoxer

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseFirebase {

    companion object {

        // --- SINGLETON ---
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: FirebaseFirestore? = null

        // --- INSTANCE ---
        fun getInstance(context: Context): FirebaseFirestore? {
            if (INSTANCE == null) {
                synchronized(DatabaseFirebase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FirebaseFirestore.getInstance()
                    }
                }
            }
            return INSTANCE
        }
    }
}