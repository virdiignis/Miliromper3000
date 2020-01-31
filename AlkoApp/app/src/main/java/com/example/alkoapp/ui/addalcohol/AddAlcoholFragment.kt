package com.example.alkoapp.ui.addalcohol

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Producer
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.ui.showalcohol.AlcoholsAdapter
import com.example.alkoapp.ui.showalcohol.ShowAlcoholFragment
import kotlinx.android.synthetic.main.fragment_add_alcohol.*
import kotlinx.android.synthetic.main.show_alcohols_fragment.*


class AddAlcoholFragment : Fragment() {

    private lateinit var viewModel: AddAlcoholViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_alcohol, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = AlcoholApi()
        val repository = AlcoholsRepository(api)

        viewModel = ViewModelProviders.of(this, AddViewMFactory(repository))
            .get(AddAlcoholViewModel::class.java)
        viewModel.getProducers()
        viewModel.getProductionCountries()
        viewModel.getTypes()


        addAlcoholConfirmButton.setOnClickListener {
            addButtonListener()
        }

        viewModel.producers.observe(viewLifecycleOwner, Observer { producers ->
            producer_spinner!!.also {
                it.adapter = SpinAdapterProducer(activity!!, producers)
            }
        })

        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            prodution_country_spinner!!.also {
                it.adapter = SpinAdapterCountry(activity!!, countries)
            }
        })

        viewModel.types.observe(viewLifecycleOwner, Observer { types ->
            type_spinner!!.also {
                it.adapter = SpinAdapterType(activity!!, types)
            }
        })

//        populateSpinners()

    }

    private fun addButtonListener() {
//        TODO: Walidacjca danych, wysyłanie

        Log.d("Listen", "Simulate adding")
        super.getFragmentManager()?.beginTransaction()?.replace(id, ShowAlcoholFragment())?.commit()


//        viewModel.addItem(example)

    }

    private fun populateSpinners() {

        Log.d("Populate", "Populating")
        val producersArray: ArrayList<String> = arrayListOf()



        val a: Array<String> = producersArray.toTypedArray()

        ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_item, a).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            type_spinner.adapter = adapter
        }
    }
}
