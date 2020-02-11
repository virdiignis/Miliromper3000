package com.example.alkoapp.data.network

import com.example.alkoapp.data.models.BartenderStuff
import com.example.alkoapp.data.models.Drink
import com.example.alkoapp.data.models.Glass
import com.example.alkoapp.data.models.Ingredient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DrinksApi {



    companion object{
        operator fun invoke() : DrinksApi {
            return Retrofit.Builder()
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


}
