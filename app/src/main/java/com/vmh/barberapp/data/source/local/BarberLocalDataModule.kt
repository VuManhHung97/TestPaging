package com.vmh.barberapp.data.source.local

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.vmh.barberapp.data.source.local.DAO.MovieRemoteKeysRxDao
import com.vmh.barberapp.data.source.local.DAO.MovieRxDao
import com.vmh.barberapp.utils.Constants
import dagger.Module
import dagger.Provides

@Module
class BarberLocalDataModule {

    @Provides
    fun provideDb(context: Application): BarberDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BarberDatabase::class.java,
            Constants.PHOTO_ROOM_DB_STRING
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesMovieKeyDAO(db: BarberDatabase): MovieRemoteKeysRxDao {
        return db.movieRemoteKeysRxDao()
    }

    @Provides
    fun providesMovieDAO(db: BarberDatabase): MovieRxDao {
        return db.moviesRxDao()
    }

    @Provides
    fun provideSharedPrefsApi(context: Application, gson: Gson): SharedPrefsApi {
        return SharedPrefsApi(context, gson)
    }

    @Provides
    fun providerGson(): Gson {
        return Gson()
    }
}
