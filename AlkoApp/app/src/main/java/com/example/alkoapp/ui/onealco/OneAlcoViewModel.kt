package com.example.alkoapp.ui.onealco

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.AlcoholRating
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.*

class OneAlcoViewModel : ViewModel() {
    private lateinit var job: Job
    val api = AlcoholApi()
    val repository = AlcoholsRepository(api)

    private val _ratings = MutableLiveData<ArrayList<AlcoholRating>>()

    val ratings: LiveData<ArrayList<AlcoholRating>>
        get() = _ratings

    fun getRatings(id: Int) = runBlocking {
        Coroutines.ioThenMain(
            { repository.getRating(id) },
            { _ratings.value = it as ArrayList<AlcoholRating>? }
        )
    }

    suspend fun getAlcohol(id: Int) = repository.getAlcohol(id)
    suspend fun deleteAlcohol(id: Int) = repository.deleteAlcohol(id)


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

    fun addRating(rating: AlcoholRating): Job {
        job = Coroutines.ioThenMain(
            {
                try {
                    repository.addRating(rating)
                } catch (e: Throwable) {
                    Log.d("ERROR", e.message.toString())
                }
            },
            {
                Log.d("Response", it.toString())
            }
        )
        return job
    }


    fun changeRating(id:Int, rating: AlcoholRating): Job {
        job = Coroutines.ioThenMain(
            {
                try {
                    repository.changeRating(id, rating)
                } catch (e: Throwable) {
                    Log.d("ERROR", e.message.toString())
                }
            },
            {
                Log.d("Response", it.toString())
            }
        )
        return job
    }


}
