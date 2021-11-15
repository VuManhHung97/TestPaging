package com.vmh.barberapp.network

import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.data.models.AccessToken
import com.vmh.barberapp.data.models.Barber
import com.vmh.barberapp.data.models.MoviesResponse
import com.vmh.barberapp.data.models.User
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface MainApi {

    @POST("logincoop")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("version") version: String,
        @Field("deviceMac") deviceMac: String
    ): Flowable<User>

    @POST("refresh")
    @FormUrlEncoded
    fun refreshToken(@Field("refreshToken") refreshToken: String): Single<AccessToken>

    @GET("movie/popular?api_key=21440930b1350cd8b4d28accce3a4799")
    fun popularMovieRx(@Query("page") page: Int) : Single<MoviesResponse>
}
