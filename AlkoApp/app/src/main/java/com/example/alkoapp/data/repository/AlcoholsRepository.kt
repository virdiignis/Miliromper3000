package com.example.alkoapp.data.repository

import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.network.SafeApiRequest
import org.json.JSONObject

class AlcoholsRepository(
    private val api: AlcoholApi
) : SafeApiRequest() {

//    suspend fun getAlcohols() = apiRequest { api.getAlcohols() }
    suspend fun getAlcohols() = apiRequest { api.getAlcohols() }

    suspend fun getProducers() = apiRequest { api.getProducers() }
    suspend fun getProductionCountries() = apiRequest { api.getProductionCountries() }
    suspend fun getTypes() = apiRequest { api.getTypes() }

    suspend fun addAlcohol (item : JSONObject) = apiRequestPost {api.addAlcohol(item) }





}