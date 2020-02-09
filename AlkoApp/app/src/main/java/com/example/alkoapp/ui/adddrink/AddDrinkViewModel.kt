package com.example.alkoapp.ui.adddrink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.Ingredient
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




    fun getIngredients() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getIngredients() },
            { _ingredients.value = it as ArrayList<Ingredient>? }
        )
    }


//TODO: Potrzebuje stworzyć spinner i do niego się odowływać
//   dodatkowy spinner tylko z jednoskami
//    f. do  dowania vidowkow
//    A no i najważniejsze adapter lub coś do tworzenia customowych View

//    fun bind(){
//        val a: Array<String> = arrayOf("No", "i", "cyk")
//
//        ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_item, a).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            type_spinner.adapter = adapter
//
//    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
