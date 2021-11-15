package com.vmh.barberapp.network

import androidx.annotation.Nullable

class TokenService {
    private var apiService: MainApi? = null

    @Nullable
    fun getApiService(): MainApi? {
        return apiService
    }

    fun setApiService(apiService: MainApi?) {
        this.apiService = apiService
    }
}
