package com.example.alkoapp.ui.adddrink

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.BartenderStuff
import com.example.alkoapp.data.models.Glass
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

    private val _glasses = MutableLiveData<ArrayList<Glass>>()

    val glasses: LiveData<ArrayList<Glass>>
        get() = _glasses

    private val _stuff = MutableLiveData<ArrayList<BartenderStuff>>()

    val stuff: LiveData<ArrayList<BartenderStuff>>
        get() = _stuff

    var ingredientProportions: ArrayList<IngredientProportions> = arrayListOf()
    var bartenderStuff: ArrayList<BartenderStuff> = arrayListOf()


    fun getIngredients() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getIngredients() },
            { _ingredients.value = it as ArrayList<Ingredient>? }
        )
    }

    fun getGlasses() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getGlasses() },
            { _glasses.value = it as ArrayList<Glass>? }
        )
    }

    fun getStuff() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getStuff() },
            { _stuff.value = it as ArrayList<BartenderStuff>? }
        )
    }


    fun addDefaultIngredient() {
        if(ingredientProportions.size != ingredients.value?.size) {
            ingredientProportions.add(
                IngredientProportions(
                    "",
                    1,
                    ingredientProportions.size,
                    "lemon",
                    "g"
                )
            )
        }

    }

    fun addBartenderStuff() {
        var size = bartenderStuff.size
        if (size == stuff.value?.size) {
//            TODO: validacja
            return
        } else {
            bartenderStuff.add(BartenderStuff("null", stuff.value?.get(size)?.name as String))
        }
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
