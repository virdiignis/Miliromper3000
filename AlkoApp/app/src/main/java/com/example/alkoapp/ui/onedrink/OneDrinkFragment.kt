package com.example.alkoapp.ui.onedrink

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alkoapp.R


class OneDrinkFragment : Fragment() {

    companion object {
        fun newInstance() = OneDrinkFragment()
    }

    private lateinit var viewModel: OneDrinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_drink_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OneDrinkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
