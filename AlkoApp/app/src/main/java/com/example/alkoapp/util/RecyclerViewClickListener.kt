package com.example.alkoapp.util

import android.view.View
import com.example.alkoapp.data.models.Drink

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, drink: Drink)

}