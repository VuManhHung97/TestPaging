package com.vmh.barberapp.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.vmh.barberapp.data.models.User
import com.vmh.barberapp.screen.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private var cachedUser = MediatorLiveData<AuthResource<out User?>>()

    fun authenticate(source: LiveData<AuthResource<out User?>>) {
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun logout() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<out User?>> {
        return cachedUser
    }

    companion object {
        private const val TAG = "SessionManager"
    }
}
