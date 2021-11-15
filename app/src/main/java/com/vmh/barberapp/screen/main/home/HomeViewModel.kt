package com.vmh.barberapp.screen.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.rxjava2.cachedIn
import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.data.models.Barber
import com.vmh.barberapp.data.models.User
import com.vmh.barberapp.data.source.BarberRepository
import com.vmh.barberapp.screen.auth.AuthResource
import com.vmh.barberapp.utils.SessionManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val barberRepository: BarberRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    var movieLiveData: MutableLiveData<PagingData<Movies.Movie>> = MutableLiveData()

    fun getAuthenticatedUser(): LiveData<AuthResource<out User?>> {
        return sessionManager.getAuthUser()
    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    fun movies(){
//        compositeDisposable.add(
//            barberRepository.getMovies().cachedIn(viewModelScope)
//                .subscribe({ data ->
//                    movieLiveData.value = data
//                }, { error ->
//                    errorLiveData.value = error
//                })
//        )


//    }
    @OptIn(ExperimentalCoroutinesApi::class)
    fun movies() =  barberRepository
    .getMovies()
    .cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val TAG = "SelectBillViewModel"
    }
}
