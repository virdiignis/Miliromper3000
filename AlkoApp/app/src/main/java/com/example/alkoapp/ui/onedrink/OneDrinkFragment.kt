package com.example.alkoapp.ui.onedrink

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Drink
import com.example.alkoapp.databinding.OneAlcoFragmentBinding
import com.example.alkoapp.databinding.OneDrinkFragmentBinding


class OneDrinkFragment(val itemDrink: Drink) : Fragment() {

    private lateinit var viewModel: OneDrinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: OneDrinkFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.one_drink_fragment, container, false)
            binding.drink = itemDrink
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OneDrinkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
