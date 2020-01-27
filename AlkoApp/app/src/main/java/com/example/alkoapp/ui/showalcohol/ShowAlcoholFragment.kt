package com.example.alkoapp.ui.showalcohol

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.ui.onealco.OneAlcoFragment
import kotlinx.android.synthetic.main.show_alcohol_fragment.*


class ShowAlcoholFragment : Fragment(), AlcoholClickListener {
    private lateinit var viewModel: ShowAlcoholViewModel
    private lateinit var factory: AlcoholsViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.show_alcohol_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = AlcoholApi()
        val repository = AlcoholsRepository(api)
        factory = AlcoholsViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, factory).get(ShowAlcoholViewModel::class.java)

        viewModel.getAlcohols()

        viewModel.alcohols.observe(viewLifecycleOwner, Observer { alcohols ->
            alcohols_recycle_view!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = AlcoholsAdapter(alcohols, this)
            }
        })
    }


    override fun onRecyclerViewItemClick(view: View, item: Alcohol) {
        Log.d("alcohol", item.name)
        super.getFragmentManager()?.beginTransaction()
            ?.replace(id, OneAlcoFragment(item))?.addToBackStack("app")?.commit()


    }

}

