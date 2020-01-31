package com.example.alkoapp.ui.onealco

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.models.Rate
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

class OneAlcoViewModel : ViewModel() {

    private lateinit var job: Job
    val api = AlcoholApi()
    val repository = AlcoholsRepository(api)

    private val _ratings = MutableLiveData<ArrayList<Rate>>()

    val ratings: LiveData<ArrayList<Rate>>
        get() = _ratings

    fun getRatings(id : Int) = runBlocking {
        job = Coroutines.ioThenMain(
            { repository.getRating(id) },
            { _ratings.value = it as ArrayList<Rate>? }
        )
    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }


}
