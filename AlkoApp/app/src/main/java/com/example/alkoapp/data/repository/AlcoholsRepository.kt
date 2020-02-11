package com.example.alkoapp.data.repository

import com.example.alkoapp.data.models.AlcoholRating
import com.example.alkoapp.data.models.AlcoholX
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.network.SafeApiRequest

class AlcoholsRepository(
    private val api: AlcoholApi
) : SafeApiRequest() {

//    suspend fun getAlcohols() = apiRequest { api.getAlcohols() }
    suspend fun getAlcohols() = apiRequest { api.getAlcohols() }

    suspend fun getAlcohol(id: Int) = apiRequest { api.getAlcohol(id) }

    suspend fun getProducers() = apiRequest { api.getProducers() }
    suspend fun getProductionCountries() = apiRequest { api.getProductionCountries() }
    suspend fun getTypes() = apiRequest { api.getTypes() }
    suspend fun getRating(id : Int) = apiRequest{api.getRatings(id)}
    suspend fun addRating(rating: AlcoholRating) = apiRequest { api.addRating(rating) }
    suspend fun changeRating(id: Int, rating: AlcoholRating) = apiRequest { api.changeRating(id, rating) }


    suspend fun addAlcohol (item : AlcoholX) = apiRequest{api.addAlcohol(item) }





}