package com.meetup.app.repository

import com.meetup.app.http.AppService
import retrofit2.Response

class MainRepository(
    var service: AppService
) {

    suspend fun getUser(): ResultOf<Any> {
        return safeApiCall {
            service.getUser()
        }
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ResultOf.Success(response.body()!!)
            } else {
                ResultOf.Error(response.errorBody()?.toString() ?: "Something went wrong")
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
            ResultOf.Error(e.message ?: "Internet error runs")
        }
    }
}