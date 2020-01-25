package com.example.alkoapp.ui.showdrinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.models.Drink
import com.example.alkoapp.models.DrinksDataset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class ShowDrinksViewModel : ViewModel() {

    private lateinit var job: Job

    private val _drinks = MutableLiveData<ArrayList<Drink>>()
    val drinks: LiveData<ArrayList<Drink>>
        get() = _drinks

//TODO: place for coroutine to collect live data
    fun getDrinks(){
        _drinks.value = DrinksDataset.createDataSet()

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }





}
