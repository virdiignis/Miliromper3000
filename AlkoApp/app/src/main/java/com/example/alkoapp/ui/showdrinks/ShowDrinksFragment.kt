package com.example.alkoapp.ui.showdrinks

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkoapp.ui.adddrink.AddDrinkFragment
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Drink
import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.repository.DrinksRepository
import com.example.alkoapp.ui.onedrink.OneDrinkFragment
import kotlinx.android.synthetic.main.show_drinks_fragment.*

class ShowDrinksFragment : Fragment(), DrinkClickListener {
    private lateinit var viewModel: ShowDrinksViewModel
    private lateinit var factory: DrinksViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.show_drinks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addDrinkButton.setOnClickListener {
            super.getFragmentManager()?.beginTransaction()
                ?.replace(
                    id,
                    AddDrinkFragment()
                )?.addToBackStack("app")?.commit()
        }

        val api = DrinksApi()
        val repository = DrinksRepository(api)
        factory = DrinksViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, factory).get(ShowDrinksViewModel::class.java)
        viewModel.getDrinks()

        viewModel.drinks.observe(viewLifecycleOwner, Observer { drinks ->
            drinks_recycle_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = DrinksAdapter(drinks, this)
            }
        })

        drinks_recycle_view.adapter = DrinksAdapter(ArrayList<Drink>(), this)

        drinksSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (drinks_recycle_view.adapter as DrinksAdapter).filter.filter(newText)
                return false
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, item: Drink) {
        Log.d("drink", item.name)
        super.getFragmentManager()?.beginTransaction()
            ?.replace(id, OneDrinkFragment(item))?.addToBackStack("app")?.commit()
    }


}
