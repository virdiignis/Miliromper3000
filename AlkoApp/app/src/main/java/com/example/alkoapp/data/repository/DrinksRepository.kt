package com.example.alkoapp.data.repository

import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.network.SafeApiRequest

class DrinksRepository(
    private val api: DrinksApi
) : SafeApiRequest() {

    suspend fun getDrinks() = apiRequest { api.getDrinks() }


}