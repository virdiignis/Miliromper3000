package com.example.alkoapp.data.repository

import com.example.alkoapp.data.models.Drink2
import com.example.alkoapp.data.models.DrinkRating
import com.example.alkoapp.data.models.IngredientProportions
import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.network.SafeApiRequest

class DrinksRepository(
    private val api: DrinksApi
) : SafeApiRequest() {

    suspend fun getDrinks() = apiRequest { api.getDrinks() }
    suspend fun getDrink(id: Int) = apiRequest { api.getDrink(id) }
    suspend fun addDrink(drink: Drink2) = apiRequest { api.addDrink(drink) }
    suspend fun getIngredients() = apiRequest { api.getIngredients() }
    suspend fun getGlasses() = apiRequest { api.getGlasses() }
    suspend fun getStuff() = apiRequest { api.getStuff() }
    suspend fun getRating(id: Int) = apiRequest { api.getRatings(id) }
    suspend fun addRating(rating: DrinkRating) = apiRequest { api.addRating(rating) }
    suspend fun changeRating(id: Int, rating: DrinkRating) =
        apiRequest { api.changeRating(id, rating) }

    suspend fun deleteDrink(id: Int) = nullableBodyRequest { api.deleteDrink(id) }
    suspend fun addProportion(proportion: IngredientProportions) {
        api.addProportion(proportion)
    }


}