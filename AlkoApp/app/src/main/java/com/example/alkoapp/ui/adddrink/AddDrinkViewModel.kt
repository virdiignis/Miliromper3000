package com.example.alkoapp.ui.adddrink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.Ingredient
import com.example.alkoapp.data.models.IngredientProportions
import com.example.alkoapp.data.repository.DrinksRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

class AddDrinkViewModel(
    val repository: DrinksRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    private lateinit var job: Job

    private val _ingredients = MutableLiveData<ArrayList<Ingredient>>()

    val ingredients: LiveData<ArrayList<Ingredient>>
        get() = _ingredients


    var ingredientProportions: ArrayList<IngredientProportions> = arrayListOf()


    fun getIngredients() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getIngredients() },
            { _ingredients.value = it as ArrayList<Ingredient>? }
        )
    }

    fun addDefaultIngredient() {
        ingredientProportions.add(IngredientProportions("", 1, ingredientProportions.size, "lemon", "g"))
    }


//TODO: Potrzebuje stworzyć spinner i do niego się odowływać
//   dodatkowy spinner tylko z jednoskami
//    f. do  dowania vidowkow
//    A no i najważniejsze adapter lub coś do tworzenia customowych View




    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
