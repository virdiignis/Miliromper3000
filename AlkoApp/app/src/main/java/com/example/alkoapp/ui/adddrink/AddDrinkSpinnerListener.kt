package com.example.alkoapp.ui.adddrink

import android.view.View

interface AddDrinkSpinnerListener {
    fun onIngredientsSpinnerChange(view: View, ingredient: String, position: Int)
    fun onUnitSpinnerChange(view: View, unit: String, position: Int)
    fun onStuffChange(view: View, name: String, position: Int)
    fun onAlcoholChange(view: View, id: Int, position: Int)
    fun onAmountEdited( amount:String, position: Int)
    fun onAlcoholUnitSpinnerChange(view: View, unit: String, position: Int)
    fun onAlcoholAmountEdited(amount: String, position: Int)


}