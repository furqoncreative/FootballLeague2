package com.furqoncreative.kadesubs2.api

import android.app.Application
import com.furqoncreative.kadesubs2.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiRepository : Application() {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY + "/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val services: ApiServices = retrofit.create(ApiServices::class.java)
}