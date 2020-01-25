package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.Alcohol
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AlcoholApi {

    @GET("alcohols")
    suspend fun getAlcohols() : Response<List<Alcohol>>

    companion object{
        operator fun invoke() : AlcoholApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://127.0.0.1:8000/")
                .build()
                .create(AlcoholApi::class.java)
        }
    }
}
