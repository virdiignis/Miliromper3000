package com.example.alkoapp.ui.addalcohol

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkoapp.data.repository.AlcoholsRepository


@Suppress("UNCHECKED_CAST")
class AddViewMFactory(
    private val repository: AlcoholsRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddAlcoholViewModel(repository) as T
    }

}