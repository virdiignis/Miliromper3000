package com.example.alkoapp.ui.adddrink

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alkoapp.R


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
        viewModel = ViewModelProviders.of(this).get(AddDrinkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
