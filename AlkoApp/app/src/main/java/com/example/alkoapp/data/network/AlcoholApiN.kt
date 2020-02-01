package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

interface AlcoholApiN {

    companion object{
        operator fun invoke() : AlcoholApiN {
            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AlcoholApiN::class.java)
        }
    }


    @Headers("Content-Type: application/json")
    @POST("/alcohols/")
    suspend  fun addAlcohol(@Body alcohol: RequestBody) : Response<Any>


}
