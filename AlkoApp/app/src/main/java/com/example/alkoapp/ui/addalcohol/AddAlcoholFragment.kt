package com.example.alkoapp.ui.addalcohol

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.data.models.AlcoholX
import com.example.alkoapp.data.network.AlcoholApi
import com.example.alkoapp.data.repository.AlcoholsRepository
import com.example.alkoapp.ui.showalcohol.ShowAlcoholFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_add_alcohol.*
import kotlinx.android.synthetic.main.recycler_opinion.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject


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

        Log.d("Validation", "Simulate walidation")
        var name: String = ""
        var alcoholContent: Float = 0.0F
        var description: String = "None :("
        var type: Int = 0
        var countryItem: String = ""
        var producerItem: String = ""


        try {
            name = this.alco_add_name.text.toString()
            alcoholContent = this.alcohol_content.text.toString().toFloat()
            description = this.description.text.toString()
//            type = this.type_spinner.selectedItem.toString()
            type = this.type_spinner.selectedItemId.toInt()
            countryItem = this.prodution_country_spinner.selectedItem.toString()
            producerItem = this.producer_spinner.selectedItem.toString()

        } catch (e: Throwable) {
            Log.d("Validation", e.message.toString())
            Log.d("Validation", "zonk")
        }


        Log.d("Validate", "Data collected")
        var flag: Boolean = true
        if (name != null && name.length >= 2) {
            Log.d("Validation", "Name valid")
        } else {
            flag = false
            Log.d("Validation", "Name not-valid")
        }

        if (description.isEmpty()) {
            Log.d("Validation", "Dec not- valid")
            description ="None :("
        }

        if (alcoholContent >= 0 && alcoholContent < 100) {
            Log.d("Validation", "Alcohol content valid")
        } else {
            flag = false
            Log.d("Validation", "alcoC not-valid")
        }



        if (flag) {


            val anotherItem = AlcoholX(
                name = name,
                description = description,
                alcohol_content = alcoholContent.toString(),
                producer = producerItem,
                production_country = countryItem,
                type = type
            )

            viewModel.addItem(anotherItem)

            super.getFragmentManager()?.beginTransaction()?.replace(id, ShowAlcoholFragment())
                ?.commit()

        }


    }

}
