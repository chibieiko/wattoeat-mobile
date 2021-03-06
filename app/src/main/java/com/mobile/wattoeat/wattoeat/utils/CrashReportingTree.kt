package com.mobile.wattoeat.wattoeat.utils

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        if (t != null) {
            if (priority == Log.ERROR) {
                Crashlytics.logException(t)
            } else if (priority == Log.WARN) {
                Crashlytics.log(priority, tag, message)
            }
        }
    }
}