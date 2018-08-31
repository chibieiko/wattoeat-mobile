package com.mobile.wattoeat.wattoeat.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.crashlytics.android.Crashlytics
import com.mobile.wattoeat.wattoeat.BuildConfig
import com.mobile.wattoeat.wattoeat.R
import com.mobile.wattoeat.wattoeat.utils.CrashReportingTree
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Fabric.with(this, Crashlytics())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        setContentView(R.layout.activity_main)
    }
}
