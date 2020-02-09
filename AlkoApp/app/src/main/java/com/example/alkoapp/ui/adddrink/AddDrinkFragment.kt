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


class AddDrinkFragment : Fragment() {

    private lateinit var viewModel: AddDrinkViewModel
    var counter = 1

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

/*
        viewModel.ingredients.observe(viewLifecycleOwner, Observer { ingredients  ->
            ingredientsTable!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = IngredientRowAdapter(ingredients, counter)
            }
        })

*/
        viewModel.ingredients.observe(viewLifecycleOwner, Observer { ingredients  ->
            ingredients_table!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = IngredientRowAdapter(ingredients, counter)
            }
        })



        add_ingredient_button.setOnClickListener { addButtonListener() }
        del_ingredient_button.setOnClickListener { delButtonListener() }

    }

    private fun addButtonListener() {
        counter += 1
        adapterUpdate()

    }
    private fun delButtonListener() {
        if(counter >1) {
            counter -= 1
//            adapterUpdate()

        }


    }

    private fun adapterUpdate(){
        viewModel.ingredients.observe(viewLifecycleOwner, Observer { ingredients ->
            ingredients_table!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = IngredientRowAdapter(ingredients, counter)
            }
        })
    }
}
