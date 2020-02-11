package com.example.alkoapp.ui.adddrink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkoapp.R


import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.repository.DrinksRepository
import kotlinx.android.synthetic.main.add_drink_fragment.*
import kotlinx.android.synthetic.main.recycler_bartender_stuff_row.*


class AddDrinkFragment : Fragment(), AddDrinkSpinnerListener {

    private lateinit var viewModel: AddDrinkViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_drink_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = DrinksApi()
        val repository = DrinksRepository(api)

        viewModel = ViewModelProviders.of(this, AddDrinkViewModelFactory(repository))
            .get(AddDrinkViewModel::class.java)

        viewModel.getIngredients()
        viewModel.getGlasses()
        viewModel.getStuff()

        viewModel.addDefaultIngredient()

        ingredientsAdapterUpdate()
        glassesAdapter()
        bartenderAdapter()


        add_ingredient_button.setOnClickListener { addButtonListener() }
        del_ingredient_button.setOnClickListener { delButtonListener() }
        add_bartender_stuff_button.setOnClickListener { addStuffButtonListener() }
        del_bartender_stuff_button.setOnClickListener { delStuffButtonListener() }
    }

    private fun addButtonListener() {
        viewModel.addDefaultIngredient()
        ingredientsAdapterUpdate()

    }


    private fun delButtonListener() {
        val sizeOfArray = viewModel.ingredientProportions.size
        if (sizeOfArray > 0) {
            viewModel.ingredientProportions.removeAt(sizeOfArray - 1)
            ingredientsAdapterUpdate()

        }
    }

    private fun addStuffButtonListener() {
        viewModel.addBartenderStuff()
        bartenderAdapter()

    }

    private fun delStuffButtonListener() {
        val sizeOfArray = viewModel.bartenderStuff.size
        if (sizeOfArray > 0) {
            viewModel.bartenderStuff.removeAt(sizeOfArray - 1)
            bartenderAdapter()

        }
    }


    private fun ingredientsAdapterUpdate() {
        viewModel.ingredients.observe(viewLifecycleOwner, Observer { ingredients ->
            ingredientsTable!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter =
                    IngredientRowAdapter(ingredients, viewModel.ingredientProportions, this)
            }
        })
    }

    private fun glassesAdapter() {
        viewModel.glasses.observe(viewLifecycleOwner, Observer { glasses ->
            glass_spinner!!.also {
                it.adapter = ServingGlassAdapter(context, glasses)
            }
        })
    }

    private fun bartenderAdapter() {
        viewModel.stuff.observe(viewLifecycleOwner, Observer { stuff ->
            bartender_stuff_table!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = BarenderStuffRowAdapter(stuff, viewModel.bartenderStuff)
            }
        })
    }

    override fun onIngredientsSpinnerChange(view: View, ingredient: String, position: Int) {
        viewModel.ingredientProportions[position].ingredient = ingredient

    }

    override fun onUnitSpinnerChange(view: View, unit: String, position: Int) {
        viewModel.ingredientProportions[position].unit = unit

    }
}
