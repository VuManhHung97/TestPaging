package com.vmh.barberapp.di.main

import com.vmh.barberapp.screen.main.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

//    @ContributesAndroidInjector
//    abstract fun contributePostsFragment(): SelectBillFragment
}
