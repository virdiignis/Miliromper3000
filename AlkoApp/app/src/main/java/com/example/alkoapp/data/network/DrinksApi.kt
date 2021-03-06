package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.*
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface DrinksApi {



    companion object{
        operator fun invoke() : DrinksApi {
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
                .create(DrinksApi::class.java)
        }
    }

    @GET("drinks/")
    suspend fun getDrinks() : Response<List<Drink>>

    @GET("drinks/ingredients/")
    suspend fun getIngredients() : Response<List<Ingredient>>

    @GET("drinks/glasses/")
    suspend fun getGlasses() : Response<List<Glass>>

    @GET(" /drinks/bartender_stuff/")
    suspend fun getStuff() : Response<List<BartenderStuff>>

    @GET("/drinks/ratings/")
    suspend fun getRatings(@Query("drink") id: Int): Response<List<DrinkRating>>

    @Headers("Content-Type: application/json")
    @POST("/drinks/ratings/")
    suspend fun addRating(@Body rating: DrinkRating): Response<Any>

    @Headers("Content-Type: application/json")
    @PUT("/drinks/ratings/{id}/")
    suspend fun changeRating(@Path(value = "id") id: Int, @Body rating: DrinkRating): Response<Any>

    @GET("/drinks/{id}/")
    suspend fun getDrink(@Path("id") id: Int): Response<Drink>

    @DELETE("/drinks/{id}/")
    suspend fun deleteDrink(@Path("id") id: Int): Response<Any>

    @Headers("Content-Type: application/json")
    @POST("/drinks/")
    suspend fun addDrink(@Body alcohol: Drink2) : Response<Drink>

    @Headers("Content-Type: application/json")
    @POST("/drinks/ingredients/proportions/")
    suspend fun addProportion(@Body proportion: IngredientProportions) : Response<Any>

    @Headers("Content-Type: application/json")
    @POST("/drinks/alcohols/proportions/")
    suspend fun addAlcoholProportion(@Body alcoholP: AlcoholProportions) : Response<Any>

}
