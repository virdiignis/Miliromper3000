package com.example.alkoapp.data.network

import android.util.Log
import android.widget.Toast
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            //TODO: FIXME XD
            Log.e("API", response.body().toString())
            throw ApiException(response.code().toString())
        }
    }

    suspend fun <T : Any> nullableBodyRequest(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()
        } else {
            //TODO: FIXME XD
            Log.e("API", response.body().toString())
            throw ApiException(response.code().toString())
        }
    }


}

class ApiException(message: String) : IOException(message)