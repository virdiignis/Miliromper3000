package com.example.alkoapp.ui.addalcohol

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.*

import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.network.ApiException
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import com.example.alkoapp.data.network.SafeApiRequest
import com.google.gson.Gson
import okhttp3.RequestBody
import org.json.JSONObject

class AddAlcoholViewModel(
    private val repository: AlcoholsRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _types = MutableLiveData<ArrayList<Type>>()
    private val _producers = MutableLiveData<ArrayList<Producer>>()
    private val _countries = MutableLiveData<ArrayList<Country>>()

    val types: LiveData<ArrayList<Type>>
        get() = _types

    val producers: LiveData<ArrayList<Producer>>
        get() = _producers

    val countries: LiveData<ArrayList<Country>>
        get() = _countries

    fun addItem(item: AlcoholX) = runBlocking {

        job = Coroutines.ioThenMain(
            {
                try {
                    repository.addAlcohol(item)

                } catch (e: Throwable) {
                    Log.d("ERROR", e.message.toString())
                }
            },
            { Log.d("Response", it.toString()) }
        )

    }

    fun getTypes() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getTypes() },
            { _types.value = it as ArrayList<Type>? }
        )
    }

    fun getProducers() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getProducers() },
            { _producers.value = it as ArrayList<Producer>? }
        )
    }

    fun getProductionCountries() = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getProductionCountries() },
            { _countries.value = it as ArrayList<Country>? }
        )
    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }


}