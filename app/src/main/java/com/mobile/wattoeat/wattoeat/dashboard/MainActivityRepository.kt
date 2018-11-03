package com.mobile.wattoeat.wattoeat.dashboard

import android.os.Handler

class MainActivityRepository {
    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") },2000)
    }
}

interface OnDataReadyCallback {
    fun onDataReady(data : String)
}
