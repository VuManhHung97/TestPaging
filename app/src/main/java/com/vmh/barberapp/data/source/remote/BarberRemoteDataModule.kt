package com.vmh.barberapp.data.source.remote

import com.vmh.barberapp.data.source.local.SharedPrefsApi
import com.vmh.barberapp.data.source.middeware.TokenAuthenticator
import com.vmh.barberapp.data.source.middeware.TokenInterceptor
import com.vmh.barberapp.network.MainApi
import com.vmh.barberapp.network.TokenService
import com.vmh.barberapp.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class BarberRemoteDataModule {

    @Provides
    fun providerRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providerMainApi(retrofit: Retrofit, apiServiceHolder: TokenService): MainApi {
        val apiService = retrofit.create(MainApi::class.java)
        apiServiceHolder.setApiService(apiService)
        return apiService
    }

    @Provides
    fun providerTokenAuthenticate(
        tokenService: TokenService,
        sharedPrefsApi: SharedPrefsApi
    ): TokenAuthenticator {
        return TokenAuthenticator(tokenService, sharedPrefsApi)
    }

    @Provides
    fun providerTokenInterceptor(sharedPrefsApi: SharedPrefsApi): TokenInterceptor {
        return TokenInterceptor(sharedPrefsApi)
    }

    @Provides
    fun providerHttpClient(
        tokenAuthenticator: TokenAuthenticator,
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .authenticator(tokenAuthenticator)
            .addInterceptor(tokenInterceptor)
            .addInterceptor(logger)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun apiServiceHolder(): TokenService {
        return TokenService()
    }

}
