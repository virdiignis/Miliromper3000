package com.example.alkoapp.ui.onedrink

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkoapp.data.models.DrinkRating
import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.repository.DrinksRepository
import com.example.alkoapp.util.Coroutines
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

class OneDrinkViewModel : ViewModel() {
    private lateinit var job: Job
    val api = DrinksApi()
    val repository = DrinksRepository(api)

    private val _ratings = MutableLiveData<ArrayList<DrinkRating>>()

    val ratings: LiveData<ArrayList<DrinkRating>>
        get() = _ratings

    fun getRatings(id: Int) = runBlocking {
        Coroutines.ioThenMain(
            { repository.getRating(id) },
            { _ratings.value = it as ArrayList<DrinkRating>? }
        )
    }

    suspend fun getDrink(id: Int) = repository.getDrink(id)
    suspend fun deleteDrink(id: Int) = repository.deleteDrink(id)


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

    fun addRating(rating: DrinkRating): Job {
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


    fun changeRating(id: Int, rating: DrinkRating): Job {
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
