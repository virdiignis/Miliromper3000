package com.example.alkoapp.ui.adddrink

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.BartenderStuff
import com.example.alkoapp.data.models.Glass
import com.example.alkoapp.data.models.Ingredient
import com.example.alkoapp.data.models.IngredientProportions
import kotlinx.android.synthetic.main.add_drink_fragment.view.*
import kotlinx.android.synthetic.main.recycler_bartender_stuff_row.view.*
import kotlinx.android.synthetic.main.recycler_ingredient_alcohol_row.view.*


class IngredientRowAdapter(
    private var ingredients: ArrayList<Ingredient>,
    var ingredientProportions: ArrayList<IngredientProportions>,
    val listener: AddDrinkSpinnerListener
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
        return ingredientProportions.size
    }

    override fun onBindViewHolder(holder: IngredientItemHolder, position: Int) {
        holder.bind(ingredients, ingredientProportions[position], listener, position)
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

    fun findItem(itemName: String): Int {
        return ingredients.indexOf(Ingredient(itemName))
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

    val unitSpinner = itemView.unit_spinner!!
    val ingredientsSpinner = itemView.ingredient_spinner!!

    private val units: Array<String> = arrayOf("ml", "oz", "g", "part", "%")

    fun bind(
        ingredients: ArrayList<Ingredient>,
        ingredient: IngredientProportions,
        listener: AddDrinkSpinnerListener,
        position: Int
    ) {
        unitSpinner.adapter =
            ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, units)
        ingredientsSpinner.adapter = IngredientSpinnerAdapter(context, ingredients)

        unitSpinner.setSelection(units.indexOf(ingredient.unit))
        ingredientsSpinner.setSelection(
            (ingredientsSpinner.adapter as IngredientSpinnerAdapter).findItem(
                ingredient.ingredient
            )
        )

        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
                listener.onUnitSpinnerChange(
                    itemView,
                    unitSpinner.selectedItem.toString(),
                    position
                )
            }
        }

        ingredientsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
                listener.onIngredientsSpinnerChange(
                    itemView,
                    ingredientsSpinner.selectedItem.toString(),
                    position
                )
            }
        }


    }


}

class ServingGlassAdapter(
    val context: Context?,
    private var glasses: ArrayList<Glass>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_spinner_item,
            parent,
            false
        ) as TextView
        view.text = glasses[position].name
        return view
    }

    override fun getItem(position: Int): Any {
        return glasses[position].name
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return glasses.size
    }
}


class BartenderStuffSpinnerAdapter(
    val context: Context?,
    private var stuff: ArrayList<BartenderStuff>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_spinner_item,
            parent,
            false
        ) as TextView
        view.text = stuff[position].name
        return view
    }

    override fun getItem(position: Int): Any {
        return stuff[position].name
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return stuff.size
    }

}


class BartenderStuffItemHolder(
    itemView: View, val context: Context?
) : RecyclerView.ViewHolder(itemView) {

    val bartenderStuffSpinner = itemView.bartender_stuff_spinner

    fun bind(
        stuff: ArrayList<BartenderStuff>,
        position: Int
//    TODO: place for listener
    ) {
        bartenderStuffSpinner.adapter = BartenderStuffSpinnerAdapter(context, stuff)
    }
}

class BarenderStuffRowAdapter(
    var stuff: ArrayList<BartenderStuff>
) : RecyclerView.Adapter<BartenderStuffItemHolder>() {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BartenderStuffItemHolder {
        return BartenderStuffItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_bartender_stuff_row,
                parent,
                false
            ), parent.context
        )
    }

    override fun getItemCount(): Int {
        return stuff.size
    }


    override fun onBindViewHolder(holder: BartenderStuffItemHolder, position: Int) {
        holder.bind(stuff,position)
    }

}