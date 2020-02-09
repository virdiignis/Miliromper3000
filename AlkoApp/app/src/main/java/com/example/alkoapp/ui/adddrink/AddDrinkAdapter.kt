package com.example.alkoapp.ui.adddrink

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Ingredient
import kotlinx.android.synthetic.main.recycler_ingredient_alcohol_row.view.*


class IngredientRowAdapter(
    private var ingredients: ArrayList<Ingredient>, var counter:Int
) : RecyclerView.Adapter<IngredientItemHolder>() {


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientItemHolder {
        return IngredientItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_ingredient_alcohol_row,
                parent,
                false
            ), parent.context
        )
    }

    override fun getItemCount(): Int {
        return counter
    }

    override fun onBindViewHolder(holder: IngredientItemHolder, position: Int) {
        holder.bind(ingredients)
    }
}

class IngredientSpinnerAdapter(
    val context: Context?,
    private var ingredients: ArrayList<Ingredient>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_spinner_item,
            parent,
            false
        ) as TextView
        view.text = ingredients[position].name
        return view
    }

    override fun getItem(position: Int): Any {
        return ingredients[position].name
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return ingredients.size
    }
}


class IngredientItemHolder(
    itemView: View, val context: Context?
) : RecyclerView.ViewHolder(itemView) {

    val unitSpinner = itemView.unit_spinner
    val ingredientsSpinner = itemView.ingredient_spinner

    val units: Array<String> = arrayOf("ml", "oz", "g", "part", "%")

    fun bind(ingredients: ArrayList<Ingredient>){
        unitSpinner.adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, units)
        ingredientsSpinner.adapter = IngredientSpinnerAdapter(context, ingredients)

    }


}