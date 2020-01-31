package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.models.Country
import com.example.alkoapp.data.models.Producer
import com.example.alkoapp.data.models.Type
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AlcoholApi {



    companion object{
        operator fun invoke() : AlcoholApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8000/")
                .build()
                .create(AlcoholApi::class.java)
        }
    }

    @GET("/alcohols/")
    suspend fun getAlcohols() : Response<List<Alcohol>>

    @GET("/producers/")
    suspend fun getProducers() : Response<List<Producer>>

    @GET("/countries/")
    suspend fun getProductionCountries() : Response<List<Country>>

    @GET("/alcohols/types/")
    suspend fun getTypes() : Response<List<Type>>




    @Headers("Content-Type: application/json")
    @POST("/alcohols/")
    suspend  fun addAlcohol(@Body alcohol: JSONObject) : Response<Any>



}
