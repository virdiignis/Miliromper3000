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
import com.example.alkoapp.data.models.Ingredient
import com.example.alkoapp.data.models.IngredientProportions


import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.repository.DrinksRepository
import kotlinx.android.synthetic.main.add_drink_fragment.*


class AddDrinkFragment : Fragment() {

    private lateinit var viewModel: AddDrinkViewModel
    var counter = 5

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


        //Counter można zasrtąpić jakimś array

        viewModel.ingredients.observe(viewLifecycleOwner, Observer { ingredients  ->
            ingredientsTable!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = IngredientRowAdapter(ingredients, counter)
            }
        })




        add_ingredient_row_button.setOnClickListener{addButtonListener()}

    }

    private fun addButtonListener() {
        counter+=1
    }


}
