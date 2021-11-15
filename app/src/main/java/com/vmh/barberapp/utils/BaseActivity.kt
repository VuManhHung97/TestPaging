package com.vmh.barberapp.utils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.vmh.barberapp.screen.auth.AuthActivity
import com.vmh.barberapp.screen.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        sessionManager.getAuthUser().observe(this, Observer {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.LOADING -> {
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "registerLiveData: LOGIN SUCCESS " + it.data!!.token)
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        Log.e(TAG, "registerLiveData: LOGIN ERROR " + it.message)
                    }

                }
            }
        })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}
