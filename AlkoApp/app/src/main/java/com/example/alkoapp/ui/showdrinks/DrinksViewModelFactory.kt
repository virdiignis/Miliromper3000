package com.example.alkoapp.ui.showdrinks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alkoapp.data.repository.DrinksRepository
import com.example.alkoapp.ui.showalcohol.ShowAlcoholViewModel


@Suppress("UNCHECKED_CAST")
class DrinksViewModelFactory(
    private val repository: DrinksRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShowDrinksViewModel(repository) as T
    }

}