package com.example.alkoapp.ui.adddrink

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders


import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.repository.DrinksRepository

import kotlinx.android.synthetic.main.add_drink_fragment.*


class AddDrinkFragment : Fragment() {

    companion object {
        fun newInstance() = AddDrinkFragment()
    }

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

        val ingredientsArray = emptyArray<Int>()

        viewModel.ingredients.observe(
            viewLifecycleOwner,
            Observer { ingredientsArray})




        val ingredientsTable = this.ingredients_table
        val tableRow : TableRow = TableRow(activity!!)
        ingredientsTable.addView(tableRow)


    }


}
