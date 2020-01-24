package com.example.alkoapp.ui.showdrinks

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkoapp.R
import kotlinx.android.synthetic.main.show_drinks_fragment.*

class ShowDrinksFragment : Fragment() {
    private lateinit var viewModel: ShowDrinksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.show_drinks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ShowDrinksViewModel::class.java)

        viewModel.getDrinks()

        viewModel.drinks.observe(viewLifecycleOwner, Observer { drinks ->
            drinksBase.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MyDrinksAdapter(drinks)
            }
        })
    }





}
