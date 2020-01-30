package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.Alcohol
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AlcoholApi {

    @GET("/alcohols")
    suspend fun getAlcohols() : Response<List<Alcohol>>

    companion object{
        operator fun invoke() : AlcoholApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8000")
                .build()
                .create(AlcoholApi::class.java)
        }
    }


    @Headers("Content-Type: application/json")
    @POST("/alcohols")
    suspend  fun addAlcohol(@Body alcohol: Alcohol) : Response<String>{


    }

}
