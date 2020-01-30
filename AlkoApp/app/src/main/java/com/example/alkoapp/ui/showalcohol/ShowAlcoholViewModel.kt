package com.example.alkoapp.ui.showalcohol

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.*

class ShowAlcoholViewModel(
    private val repository: AlcoholsRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _alcohols = MutableLiveData<ArrayList<Alcohol>>()

    val alcohols: LiveData<ArrayList<Alcohol>>
        get() = _alcohols
    private val _answer = MutableLiveData<String>()

    fun getAlcohols() = runBlocking {

        job = Coroutines.ioThenMain(
            { repository.getAlcohols() },
            { _alcohols.value = it as ArrayList<Alcohol>? }
        )
    }

    fun addAlcohol(item : Alcohol) = runBlocking {

        job = Coroutines.ioThenMain(
            { repository.addAlcohol(item) },
            { println( it.toString() )}
        )


    }



    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }


}
