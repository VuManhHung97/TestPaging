package com.vmh.barberapp.di

import android.app.Application
import com.vmh.barberapp.data.source.BarberRepositoryModule
import com.vmh.barberapp.utils.BaseApplication
import com.vmh.barberapp.utils.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class,
    ViewModelFactoryModule::class,
    BarberRepositoryModule::class
])
interface ApplicationComponent: AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
