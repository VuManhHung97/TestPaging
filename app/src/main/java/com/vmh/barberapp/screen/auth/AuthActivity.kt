package com.vmh.barberapp.screen.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.vmh.barberapp.R
import com.vmh.barberapp.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initView()
        registerLiveData()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)
    }

    private fun registerLiveData() {
        viewModel.observerAuthState().observe(this, {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.LOADING -> {
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "registerLiveData: LOGIN SUCCESS " + it.data!!.message)
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        Log.e(TAG, "registerLiveData: LOGOUT")
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        Log.e(TAG, "registerLiveData: LOGIN ERROR " + it.message)
                    }

                }
            }
        })
    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}
