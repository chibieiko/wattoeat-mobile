package com.mobile.wattoeat.wattoeat.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.mobile.wattoeat.wattoeat.BuildConfig
import com.mobile.wattoeat.wattoeat.R
import com.mobile.wattoeat.wattoeat.auth.FirebaseUIActivity
import com.mobile.wattoeat.wattoeat.databinding.ActivityMainBinding
import com.mobile.wattoeat.wattoeat.databinding.NavHeaderMainBinding
import com.mobile.wattoeat.wattoeat.options.AddOptionActivity
import com.mobile.wattoeat.wattoeat.utils.CrashReportingTree
import com.squareup.picasso.Picasso
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NavHeaderMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfUserLoggedIn()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        headerBinding = NavHeaderMainBinding.bind(binding.navView
                .getHeaderView(0))
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.loadOptions()

        headerBinding.viewModel = viewModel
        headerBinding.executePendingBindings()

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        Fabric.with(this, Crashlytics())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun checkIfUserLoggedIn() {
        val user = FirebaseAuth.getInstance().currentUser

        if (user == null) {
            val intent = Intent(this, FirebaseUIActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        val url = viewModel.user.get()?.photoUrl
        val imageView: ImageView? = navHeaderImageView
        if (viewModel.user.get() != null && imageView != null) {
            Picasso.get()
                    .load(url)
                    .fit()
                    .into(imageView)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_add_option -> {
                AddOptionActivity.startActivity(this)
            }
            R.id.nav_logout -> {
                this.signOut()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    val intent = Intent(this, FirebaseUIActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                            this,
                            R.string.success_log_out,
                            Toast.LENGTH_SHORT
                    ).show()
                }
    }
}
