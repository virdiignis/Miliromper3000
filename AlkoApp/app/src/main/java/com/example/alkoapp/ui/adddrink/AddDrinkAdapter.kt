package com.example.alkoapp.ui.adddrink

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.*
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
    val ingredientsSpinner = itemView.ingredient_alcohols_spinner!!
    val amountBox = itemView.amount

    private val units: Array<String> = arrayOf("ml", "oz", "g", "part", "%")

    fun bind(
        ingredients: ArrayList<Ingredient>,
        ingredient: IngredientProportions,
        listener: AddDrinkSpinnerListener,
        position: Int
    ) {
        amountBox.setText(ingredient.amount)
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

        amountBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                listener.onAmountEdited(

                    amountBox.text.toString(),
                    position
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


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

    fun findItem(itemName: String): Int {
        return stuff.indexOf(stuff.find { it.name == itemName })
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

    val bartenderStuffSpinner = itemView.bartender_stuff_spinner!!

    fun bind(
        stuff: ArrayList<BartenderStuff>,
        currentStuff: String,
        listener: AddDrinkSpinnerListener,
        position: Int
    ) {
        bartenderStuffSpinner.adapter = BartenderStuffSpinnerAdapter(context, stuff)

        bartenderStuffSpinner.setSelection(
            (bartenderStuffSpinner.adapter as BartenderStuffSpinnerAdapter)
                .findItem(currentStuff)
        )

        bartenderStuffSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
                listener.onStuffChange(
                    itemView,
                    bartenderStuffSpinner.selectedItem.toString(),
                    position
                )
            }
        }
    }
}

class BartenderStuffRowAdapter(
    val stuff: ArrayList<BartenderStuff>,
    val current_stuff: ArrayList<String>,
    val listener: AddDrinkSpinnerListener
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
        return current_stuff.size
    }


    override fun onBindViewHolder(holder: BartenderStuffItemHolder, position: Int) {
        holder.bind(stuff, current_stuff[position], listener, position)
    }

}


class AlcoholRowAdapter(
    private var alcohols: ArrayList<Alcohol>,
    var currentAlcohols: ArrayList<AlcoholProportions>,
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
        return currentAlcohols.size
    }

    override fun onBindViewHolder(holder: IngredientItemHolder, position: Int) {

    }
}


class AlcoholItemHolder(
    itemView: View, val context: Context?
) : RecyclerView.ViewHolder(itemView) {
    val unitSpinner = itemView.unit_spinner!!
    val alcoholsSpinner = itemView.ingredient_alcohols_spinner

    private val units: Array<String> = arrayOf("ml", "oz", "part", "%")

    fun bind(
        alcohols: ArrayList<Alcohol>,
        alcoholProportion: AlcoholProportions,
        listener: AddDrinkSpinnerListener,
        position: Int
    ) {
        unitSpinner.adapter =
            ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, units)

        unitSpinner.setSelection(units.indexOf(alcoholProportion.unit))

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

        alcoholsSpinner.adapter = AlcoholSpinnerAdapter(context, alcohols)
        alcoholsSpinner.setSelection(
            (alcoholsSpinner.adapter as AlcoholSpinnerAdapter).findItem(alcoholProportion.id)
        )

        alcoholsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
                listener.onAlcoholChange(
                    itemView,
                    alcoholsSpinner.selectedItemPosition,
                    position
                )
            }
        }
    }


}


class AlcoholSpinnerAdapter(
    val context: Context?,
    private var alcohols: ArrayList<Alcohol>

) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_spinner_item,
            parent,
            false
        ) as TextView
        view.text = alcohols[position].name
        return view
    }

    override fun getItem(position: Int): Any {
        return alcohols[position].name
    }

    fun findItem(id: Int): Int {
        return alcohols.indexOf(alcohols.find { it.id == id })
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return alcohols.size
    }
}


