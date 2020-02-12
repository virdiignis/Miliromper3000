package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.Credentials
import com.example.alkoapp.data.models.Token
import com.example.alkoapp.data.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AuthApi {
    companion object {
        operator fun invoke(): AuthApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://10.0.2.2:8000")
                .build()
                .create(AuthApi::class.java)
        }
    }

    @Headers("Content-Type: application/json")
    @POST("/auth/token/login/")
    suspend fun login(@Body credentials: Credentials): Response<Token>

    @GET("/me/")
    suspend fun me(@Header("Authorization") authToken: String): Response<User>

    @POST("/users/")
    suspend fun register(@Body u: User): Response<Any>

}