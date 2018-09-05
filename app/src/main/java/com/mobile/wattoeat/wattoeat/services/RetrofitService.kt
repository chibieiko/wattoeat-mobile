package com.mobile.wattoeat.wattoeat.services

import com.mobile.wattoeat.wattoeat.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


fun makeRetrofit(vararg interceptors: Interceptor) = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.apiUrl)
        .client(makeHttpClient(interceptors))
        .build()

fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
        .addInterceptor(headersInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .build()

fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
            .addHeader("Authorization", BuildConfig.apiSecret)
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build())
}