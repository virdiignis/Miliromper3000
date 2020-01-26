package com.example.alkoapp.ui.showalcohol

import android.view.View
import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.models.Drink

interface AlcoholClickListener {
    fun onRecyclerViewItemClick(view: View, item: Alcohol)


}