package com.mobile.wattoeat.wattoeat.dashboard

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivityViewModel: ViewModel() {
    val repository = MainActivityRepository()
    var isLoading = false
    var user = ObservableField<FirebaseUser>(FirebaseAuth.getInstance().currentUser)

    fun refresh() {
        isLoading = true
        repository.refreshData(object : OnDataReadyCallback {
            override fun onDataReady(data: String) {
                isLoading = false
            }
        })
    }
}