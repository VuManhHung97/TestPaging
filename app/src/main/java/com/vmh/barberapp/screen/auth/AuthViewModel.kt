package com.vmh.barberapp.screen.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.vmh.barberapp.data.models.User
import com.vmh.barberapp.data.source.BarberRepository
import com.vmh.barberapp.utils.SessionManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val photoRepository: BarberRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is working...")
    }

    fun authenticate(
        username: String,
        password: String,
        version: String,
        deviceMac: String
    ) {
        Log.d(TAG, "authenticateWithId: attempting to login.")
        sessionManager.authenticate(queryUser(username, password, version, deviceMac))
    }

    private fun queryUser(
        username: String,
        password: String,
        version: String,
        deviceMac: String
    ): LiveData<AuthResource<out User?>> {
        return LiveDataReactiveStreams.fromPublisher(photoRepository.login(
            username,
            password,
            version,
            deviceMac
        )
            .onErrorReturn {
                val errorUser = User()
                errorUser.id = -1
                errorUser
            }.map {
                if (it.id == -1) {
                    AuthResource.error("Could not authenticate", null)
                } else {
                    AuthResource.authenticated(it)
                }
            }.subscribeOn(Schedulers.io())
        )
    }

    fun observerAuthState(): LiveData<AuthResource<out User?>> {
        return sessionManager.getAuthUser()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }
}
