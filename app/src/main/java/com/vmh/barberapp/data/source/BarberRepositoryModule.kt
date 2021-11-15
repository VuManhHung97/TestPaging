package com.vmh.barberapp.data.source

import com.vmh.barberapp.data.models.MoviesMapper
import com.vmh.barberapp.data.source.local.BarberDatabase
import com.vmh.barberapp.data.source.local.BarberLocalDataModule
import com.vmh.barberapp.data.source.local.BarberLocalDataSource
import com.vmh.barberapp.data.source.local.DAO.MovieRxDao
import com.vmh.barberapp.data.source.local.SharedPrefsApi
import com.vmh.barberapp.data.source.remote.BarberRemoteDataModule
import com.vmh.barberapp.data.source.remote.BarberRemoteDataSource
import com.vmh.barberapp.network.MainApi
import dagger.Module
import dagger.Provides
import java.util.*

@Module(includes = [BarberLocalDataModule::class, BarberRemoteDataModule::class])
class BarberRepositoryModule {

    @Provides
    fun providerBarberLocalDataSource(
        sharedPrefsApi: SharedPrefsApi,
        movieRxDao: MovieRxDao
    ): BarberLocalDataSource {
        return BarberLocalDataSource(sharedPrefsApi, movieRxDao)
    }

    @Provides
    fun providerBarberRemoteDataSource(mainApi: MainApi): BarberRemoteDataSource {
        return BarberRemoteDataSource(mainApi)
    }

    @Provides
    fun providerMovieRemoteMediator(
        mainApi: MainApi,
        database: BarberDatabase
    ): GetMoviesRxRemoteMediator {
        return GetMoviesRxRemoteMediator(
            mainApi,
            database,
            MoviesMapper(),
            Locale.getDefault()
        )
    }


}
