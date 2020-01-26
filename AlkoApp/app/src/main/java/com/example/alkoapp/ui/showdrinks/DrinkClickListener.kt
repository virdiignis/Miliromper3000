package com.example.alkoapp.ui.showdrinks

import android.view.View
import com.example.alkoapp.data.models.Drink

interface DrinkClickListener {
    fun onRecyclerViewItemClick(view : View, item: Drink)
}
