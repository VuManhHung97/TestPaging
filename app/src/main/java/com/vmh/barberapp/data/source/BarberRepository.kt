package com.vmh.barberapp.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.data.models.Barber
import com.vmh.barberapp.data.models.User
import com.vmh.barberapp.data.source.local.BarberLocalDataSource
import com.vmh.barberapp.data.source.remote.BarberRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class BarberRepository @Inject constructor(
    private val local: BarberLocalDataSource,
    private val remote: BarberRemoteDataSource
) {

    @Inject
    lateinit var getMoviesRxRemoteMediator: GetMoviesRxRemoteMediator

    fun login(
        username: String,
        password: String,
        version: String,
        deviceMac: String
    ): Flowable<User> {
        return remote.login(username, password, version, deviceMac)
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getMovies(): Flowable<PagingData<Movies.Movie>> {
        val pagingSourceFactory = { local.selectAllMovie() }
        return Pager(
            config = PagingConfig(
                pageSize = 12,
                enablePlaceholders = false,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            remoteMediator = getMoviesRxRemoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flowable
    }

}
