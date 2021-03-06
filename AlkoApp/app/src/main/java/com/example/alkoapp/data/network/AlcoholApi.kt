package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.*
import com.google.gson.Gson
import com.google.gson.JsonArray
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface AlcoholApi {

    companion object {
        operator fun invoke(): AlcoholApi {
            val okClient = OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.MINUTES)
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
            return Retrofit.Builder()
                .client(okClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://10.0.2.2:8000/")
                .build()
                .create(AlcoholApi::class.java)
        }
    }

    @GET("/alcohols/")
    suspend fun getAlcohols(): Response<List<Alcohol>>

    @GET("/producers/")
    suspend fun getProducers(): Response<List<Producer>>

    @GET("/countries/")
    suspend fun getProductionCountries(): Response<List<Country>>

    @GET("/alcohols/types/")
    suspend fun getTypes(): Response<List<Type>>

    @GET("/alcohols/ratings/")
    suspend fun getRatings(@Query("alcohol") id: Int): Response<List<AlcoholRating>>


    @Headers("Content-Type: application/json")
    @POST("/alcohols/")
    suspend fun addAlcohol(@Body alcohol: AlcoholX): Response<Any>

    @Headers("Content-Type: application/json")
    @POST("/alcohols/ratings/")
    suspend fun addRating(@Body rating: AlcoholRating): Response<Any>

    @Headers("Content-Type: application/json")
    @PUT("/alcohols/ratings/{id}/")
    suspend fun changeRating(@Path(value = "id") id: Int, @Body rating: AlcoholRating): Response<Any>

    @GET("/alcohols/{id}/")
    suspend fun getAlcohol(@Path("id") id: Int): Response<Alcohol>

    @DELETE("/alcohols/{id}/")
    suspend fun deleteAlcohol(@Path("id") id: Int): Response<Any>
}
