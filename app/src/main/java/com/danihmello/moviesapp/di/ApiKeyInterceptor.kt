package com.danihmello.moviesapp.di

import com.danihmello.moviesapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(newRequest)
    }
}