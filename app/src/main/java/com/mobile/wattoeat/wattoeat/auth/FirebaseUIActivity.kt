package com.mobile.wattoeat.wattoeat.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.mobile.wattoeat.wattoeat.BuildConfig
import com.mobile.wattoeat.wattoeat.R
import com.mobile.wattoeat.wattoeat.dashboard.MainActivity

class FirebaseUIActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = BuildConfig.firebaseAuthRequestCode
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_ui)
        createSignInIntent()
    }

    private fun createSignInIntent() {
        val authProviders = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(authProviders)
                        .build(),
                RC_SIGN_IN.toInt()
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN.toInt()) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                        applicationContext,
                        R.string.error_log_in,
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
