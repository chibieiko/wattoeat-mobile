package com.mobile.wattoeat.wattoeat.dashboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mobile.wattoeat.wattoeat.options.OnOptionsReadyCallback
import com.mobile.wattoeat.wattoeat.options.Option
import com.mobile.wattoeat.wattoeat.options.OptionRepository
import com.mobile.wattoeat.wattoeat.services.ApiService

class MainActivityViewModel: ViewModel() {
    val optionRepository = OptionRepository()
    var isLoading = ObservableBoolean(false)
    var user = ObservableField<FirebaseUser>(FirebaseAuth.getInstance().currentUser)
    var options = MutableLiveData<ArrayList<Option>>()

    val apiService: ApiService = ApiService.apiService

    fun loadOptions() {
        isLoading.set(true)
        optionRepository.getOptions(object : OnOptionsReadyCallback {
            override fun onDataReady(data: ArrayList<Option>) {
                isLoading.set(false)
                options.value = data
            }
        })
    }
}