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
import com.example.alkoapp.data.models.Drink
import com.example.alkoapp.data.network.DrinksApi
import com.example.alkoapp.data.repository.DrinksRepository
import com.example.alkoapp.util.RecyclerViewClickListener
import kotlinx.android.synthetic.main.show_drinks_fragment.*

class ShowDrinksFragment : Fragment(),
    RecyclerViewClickListener {
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

        val api = DrinksApi()
        val repository = DrinksRepository(api)
        factory = DrinksViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, factory).get(ShowDrinksViewModel::class.java)
        viewModel.getDrinks()

        viewModel.drinks.observe(viewLifecycleOwner, Observer { drinks ->
            drinksBase.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MyDrinksAdapter(drinks, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, drink: Drink) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
