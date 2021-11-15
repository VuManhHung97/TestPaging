package com.vmh.barberapp.di

import com.vmh.barberapp.di.auth.AuthViewModelModule
import com.vmh.barberapp.di.main.MainFragmentBuildersModule
import com.vmh.barberapp.di.main.MainViewModelModule
import com.vmh.barberapp.screen.auth.AuthActivity
import com.vmh.barberapp.screen.main.MainActivity
import com.vmh.barberapp.di.scopes.AuthScope
import com.vmh.barberapp.di.scopes.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainViewModelModule::class, MainFragmentBuildersModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}
