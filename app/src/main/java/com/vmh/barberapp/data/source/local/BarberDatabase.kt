package com.vmh.barberapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.data.models.Barber
import com.vmh.barberapp.data.models.Converters
import com.vmh.barberapp.data.models.RemoteKey
import com.vmh.barberapp.data.models.Search
import com.vmh.barberapp.data.source.local.DAO.MovieRemoteKeysRxDao
import com.vmh.barberapp.data.source.local.DAO.MovieRxDao

@Database(
    entities = [Search::class, Barber::class, RemoteKey::class, Movies.Movie::class, Movies.MovieRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BarberDatabase : RoomDatabase() {
    abstract fun moviesRxDao(): MovieRxDao
    abstract fun movieRemoteKeysRxDao(): MovieRemoteKeysRxDao
}
