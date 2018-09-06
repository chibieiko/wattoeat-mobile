package com.mobile.wattoeat.wattoeat.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.mobile.wattoeat.wattoeat.R
import com.mobile.wattoeat.wattoeat.adapters.UserAdapter
import com.mobile.wattoeat.wattoeat.services.ApiService.Companion.apiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        rv_users.layoutManager = GridLayoutManager(this, 2)
        fetchUsers()
    }

    fun fetchUsers(): Unit {
        disposable = apiService.getUsers()
                // fetch the data on background, a separate thread from main UI thread
                .subscribeOn(Schedulers.io())
                // observeOn allows us to define in which thread subscribe methods are executed on
                // make changes on the Main UI thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> rv_users.adapter = UserAdapter(result, this) },
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
