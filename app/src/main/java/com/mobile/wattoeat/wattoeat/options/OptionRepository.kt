package com.mobile.wattoeat.wattoeat.options

import android.os.Handler

class OptionRepository {
    fun getOptions(onOptionsReadyCallback: OnOptionsReadyCallback) {
        val optionsList = ArrayList<Option>()
        optionsList.add(Option("Makaronilaatikko"))
        optionsList.add(Option("Makkarakeitto"))
        optionsList.add(Option("Kanakastike"))
        optionsList.add(Option("Pizza"))

        Handler().postDelayed(
                {onOptionsReadyCallback.onDataReady(optionsList)},
                2000
        )
    }
}

interface OnOptionsReadyCallback {
    fun onDataReady(data: ArrayList<Option>)
}