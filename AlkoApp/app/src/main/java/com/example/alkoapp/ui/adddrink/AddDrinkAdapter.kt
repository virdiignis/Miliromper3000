package com.example.alkoapp.ui.adddrink

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.data.models.Ingredient


class IngredientsSpinnerAdapter(val context: Context?, private var ingredients : ArrayList<Ingredient>) : BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false) as TextView
        view.text = ingredients[position].name
        return view
    }


    override fun getItem(position: Int): Any {
        return ingredients[position].name
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
       return ingredients.size
    }

}
class IngreedientItemHodler(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {



}