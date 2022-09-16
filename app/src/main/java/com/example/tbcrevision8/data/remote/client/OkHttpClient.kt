package com.example.tbcrevision8.data.remote.client

import android.content.Context
import com.example.tbcrevision8.extension.isNetworkAvailable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.OkHttpClient


object OkHttpClient {

    fun getInstance(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getOfflineInterceptor(context))
            .addNetworkInterceptor(getOnlineInterceptor())
            .cache(getCache(context))
            .build()
    }

    private fun getOnlineInterceptor() = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    private fun getOfflineInterceptor(context: Context) = Interceptor { chain ->
        var request: Request = chain.request()
        if (!context.isNetworkAvailable()) {
            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

    private fun getCache(context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        return Cache(context.cacheDir, cacheSize)
    }

}