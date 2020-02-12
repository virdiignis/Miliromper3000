package com.example.alkoapp.ui.adddrink

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.*
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.data.repository.DrinksRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

class AddDrinkViewModel(
    val repository: DrinksRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    private lateinit var job: Job

    private val _alcohols = MutableLiveData<ArrayList<Alcohol>>()

    val alcohols: LiveData<ArrayList<Alcohol>>
        get() = _alcohols

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
    var bartenderStuff: ArrayList<String> = arrayListOf()
    var alcoholsProportions: ArrayList<AlcoholProportions> = arrayListOf()

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

    fun getAlcohols() = runBlocking {

        job = Coroutines.ioThenMain(
            { AlcoholsRepository(AlcoholApi()).getAlcohols() },
            { _alcohols.value = it as ArrayList<Alcohol>? }
        )
    }




    fun addDefaultIngredient() {
        if (ingredientProportions.size != ingredients.value?.size) {
            ingredientProportions.add(
                IngredientProportions(
                    "0",
                    12,
                    ingredientProportions.size,
                    "lemon",
                    "g"
                )
            )
        }

    }

    fun addBartenderStuff() {
        val size = bartenderStuff.size
        if (size == stuff.value?.size) {
//            TODO: validacja
            return
        } else {
            bartenderStuff.add(stuff.value?.get(size)?.name as String)
        }
    }

    fun addAlcoholProportion() {
        val size = alcoholsProportions.size
        if (size == alcohols.value?.size) {
//           FIXME
            return
        } else {
            alcoholsProportions.add(
                AlcoholProportions(
                    alcohol = 1,
                    amount = "0",
                    id = size,
                    unit = "ml",
                    drink = 0
                )
            )
        }
    }

    fun addItem(item: Drink2, ingredientProportions: List<IngredientProportions>, alcohols : List<AlcoholProportions>) = runBlocking {

        job = Coroutines.ioThenMain(
            {
                try {
                    var temp = repository.addDrink(item)
                    for (proportion in ingredientProportions){
                        proportion.drink = temp.id
                        repository.addProportion(proportion)
                    }
                    for (alcohol in alcohols){
                        alcohol.drink = temp.id
                        repository.addAlcoholProportion(alcohol)
                    }

                } catch (e: Throwable) {
                    Log.d("ERROR", e.message.toString())
                }
            },
            { Log.d("Response", it.toString()) }
        )

    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
