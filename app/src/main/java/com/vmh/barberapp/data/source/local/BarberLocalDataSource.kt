package com.vmh.barberapp.data.source.local

import androidx.paging.PagingSource
import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.data.source.BarberDataSource
import com.vmh.barberapp.data.source.local.DAO.MovieRxDao
import javax.inject.Inject

class BarberLocalDataSource @Inject constructor(
    private val sharedPrefsApi: SharedPrefsApi,
    private val movieDAO: MovieRxDao
): BarberDataSource.BarberLocalDataSource {

    override fun selectAllMovie(): PagingSource<Int, Movies.Movie> {
        return movieDAO.selectAll()
    }
}
