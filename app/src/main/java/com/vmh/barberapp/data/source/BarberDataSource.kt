package com.vmh.barberapp.data.source

import androidx.paging.PagingSource
import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.data.models.Barber
import com.vmh.barberapp.data.models.User
import io.reactivex.Flowable
import io.reactivex.Single

interface BarberDataSource {
    interface BarberLocalDataSource {
        fun selectAllMovie(): PagingSource<Int, Movies.Movie>
    }

    interface BarberRemoteDataSource {
        fun login(username: String,
                  password: String,
                  version: String,
                  deviceMac: String): Flowable<User>

    }
}
