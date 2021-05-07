package com.meetup.app.http

import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("/users")
    suspend fun getUser(): Response<Any>
}