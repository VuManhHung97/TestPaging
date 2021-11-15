package com.vmh.barberapp.data.source.remote

import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.data.models.Barber
import com.vmh.barberapp.data.models.User
import com.vmh.barberapp.data.source.BarberDataSource
import com.vmh.barberapp.network.MainApi
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class BarberRemoteDataSource @Inject constructor(private val mainApi: MainApi) :
    BarberDataSource.BarberRemoteDataSource {
    override fun login(
        username: String,
        password: String,
        version: String,
        deviceMac: String
    ): Flowable<User> {
        return mainApi.login(username, password, version, deviceMac)
    }

}
