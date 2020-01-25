package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.Drink
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DrinksApi {

    @GET("drinks")
    suspend fun getDrinks() : Response<List<Drink>>

    companion object{
        operator fun invoke() : DrinksApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://127.0.0.1:8000/")
                .build()
                .create(DrinksApi::class.java)
        }
    }
}
