package com.vmh.barberapp.di.main


import androidx.lifecycle.ViewModel
import com.vmh.barberapp.di.ViewModelKey
import com.vmh.barberapp.screen.main.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindProfileViewModel(viewModel: HomeViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(SelectOptionViewModel::class)
//    abstract fun bindPostsViewModel(viewModel: SelectOptionViewModel): ViewModelKey
}
