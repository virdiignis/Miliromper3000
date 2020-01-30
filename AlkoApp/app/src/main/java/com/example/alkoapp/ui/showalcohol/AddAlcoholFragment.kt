package com.example.alkoapp.ui.showalcohol

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.alkoapp.R
import kotlinx.android.synthetic.main.fragment_add_alcohol.*


class AddAlcoholFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_alcohol, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populateSpinners()
    }

    private fun populateSpinners() {
        val a: Array<String> = arrayOf("No", "i", "cyk")

        ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_item, a).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            type_spinner.adapter = adapter
        }
    }
}
