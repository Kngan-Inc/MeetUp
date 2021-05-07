package com.meetup.app.http

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.meetup.app.MeetUpApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private val httpClient = OkHttpClient
    .Builder()
    .addInterceptor(ChuckerInterceptor.Builder(MeetUpApplication.appContext).build())
    .build()

private val appRetrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal val appService: AppService = appRetrofit.create()