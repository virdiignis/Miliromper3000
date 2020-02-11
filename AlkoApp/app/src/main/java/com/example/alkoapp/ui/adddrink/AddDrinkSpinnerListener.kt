package com.example.alkoapp.ui.adddrink

import android.view.View

interface AddDrinkSpinnerListener {
    fun onIngredientsSpinnerChange(view: View, ingredient: String, id: Int)
    fun onUnitSpinnerChange(view: View, unit: String, id: Int)


}