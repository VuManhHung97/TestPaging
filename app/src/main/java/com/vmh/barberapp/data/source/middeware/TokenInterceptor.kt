package com.vmh.barberapp.data.source.middeware

import com.vmh.barberapp.data.models.AccessToken
import com.vmh.barberapp.data.source.local.SharedPrefsApi
import com.vmh.barberapp.data.source.local.SharedPrefsKey
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val sharedPrefsApi: SharedPrefsApi) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        synchronized(this) {
            val original = chain.request()
            if (original.url.encodedPath.contains("logincoop") || original.url.encodedPath.contains("popular")
            ) return chain.proceed(original)
            if (original.url.encodedPath.contains("refresh")) {
                val originalHttpUrl = original.url
                val refreshResult =
                    sharedPrefsApi[SharedPrefsKey.ACCESS_TOKEN, AccessToken::class.java]
                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", "Bearer ${refreshResult!!.refreshToken}")
                    .url(originalHttpUrl)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
            val originalHttpUrl = original.url
            val refreshResult = sharedPrefsApi[SharedPrefsKey.ACCESS_TOKEN, AccessToken::class.java]
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Bearer ${refreshResult!!.token}")
                .url(originalHttpUrl)
            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }
}
