package com.example.alkoapp.ui.showdrinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.Drink
import com.example.alkoapp.data.repository.DrinksRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.Job

class ShowDrinksViewModel(
    private  val repository: DrinksRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _drinks = MutableLiveData<ArrayList<Drink>>()
    val drinks: LiveData<ArrayList<Drink>>
        get() = _drinks

//TODO: place for coroutine to collect live data
    fun getDrinks(){
        job = Coroutines.ioThenMain(
            {repository.getDrinks()},
            {_drinks.value = it as ArrayList<Drink>? }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }





}
