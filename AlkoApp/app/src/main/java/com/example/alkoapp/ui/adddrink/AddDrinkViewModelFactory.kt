package com.example.alkoapp.ui.adddrink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkoapp.data.repository.DrinksRepository


@Suppress("UNCHECKED_CAST")
class AddDrinkViewModelFactory(
    private val repository: DrinksRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddDrinkViewModel(repository) as T
    }

}