package com.example.alkoapp.ui.showalcohol

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkoapp.data.repository.AlcoholsRepository


@Suppress("UNCHECKED_CAST")
class AlcoholsViewModelFactory(
    private val repository: AlcoholsRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowAlcoholViewModel(repository) as T
    }

}