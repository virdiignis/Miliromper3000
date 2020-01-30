package com.example.alkoapp.data.repository

import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.network.SafeApiRequest

class AlcoholsRepository(
    private val api: AlcoholApi
) : SafeApiRequest() {

//    suspend fun getAlcohols() = apiRequest { api.getAlcohols() }
    suspend fun getAlcohols() = apiRequest { api.getAlcohols() }
    suspend fun addAlcohol (item : Alcohol) = apiRequestPost {
        api.addAlcohol(item) }


}